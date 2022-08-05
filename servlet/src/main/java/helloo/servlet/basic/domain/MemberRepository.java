package helloo.servlet.basic.domain;

import helloo.servlet.basic.Dto.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    // 동시성을 고려하지 않은 싱글톤 패턴
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 1L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    public MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}