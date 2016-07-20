package mailchimp.clients;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import mailchimp.attributes.Member;
import mailchimp.communicators.MemberCommunicator;
import mailchimp.util.GenerateMD5;

public class MemberClient {

	private MemberCommunicator memberCommunicator;
	
	public MemberClient(){
	}

	public MemberClient(MemberCommunicator memberCommunicator) {
		this.memberCommunicator = memberCommunicator;
	}

	public List<Member> list(String idList, Integer count) {
		Member member = memberCommunicator.readAll(idList, count);
		return member.getMembers();
	}
	
	public Integer countMembersList(String idList) {
		Member member = memberCommunicator.count(idList);
		return member.getTotalItems();
	}

	public Member readOne(String idList, String email) throws NoSuchAlgorithmException {
		String subscriberHash = GenerateMD5.stringToMd5(email);
		Member member = memberCommunicator.readOne(idList, subscriberHash);
		return member;
	}

	public Member update(String idList, String email, Member member) throws NoSuchAlgorithmException {
		String subscriberHash = GenerateMD5.stringToMd5(email);
		Member memberUpdated = memberCommunicator.update(idList, subscriberHash, member);
		return memberUpdated;
	}

	public void delete(String idList, String email) throws NoSuchAlgorithmException {
		String subscriberHash = GenerateMD5.stringToMd5(email);
		memberCommunicator.delete(idList, subscriberHash);
	}

	public Member create(String idList, Member members) {
		return memberCommunicator.create(idList, members);
	}
	
}
