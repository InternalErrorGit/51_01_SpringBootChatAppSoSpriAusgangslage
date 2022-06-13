package ch.bbw.pg.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Service
@Transactional
public class MemberService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Iterable<Member> getAll() {
        logger.info("DB Transaction: select");
        return repository.findAll();
    }

    public Member filterByUsername(String username) {
        Iterable<Member> memberIterable = getAll();

        for (Member m : memberIterable) {
            if (m.getUsername().equals(username)) return m;
        }
        return null;

    }

    public void add(Member member) {
        logger.info("DB Transaction: insert");
        repository.save(member);
    }

    public void update(Member member) {
        logger.info("DB Transaction: update");
        repository.save(member);
    }

    public void deleteById(Long id) {
        logger.info("DB Transaction: delete");
        repository.deleteById(id);
    }

    public Member getById(Long id) {
        logger.info("DB Transaction: select");
        Iterable<Member> memberIterable = repository.findAll();

        for (Member member : memberIterable) {
            if (Objects.equals(member.getId(), id)) {
                return member;
            }
        }
        logger.info("DB Transaction failed: select: id does not exist in repository: " + id);
        return null;
    }

    public Member getByUserName(String username) {
        logger.info("DB Transaction: select");
        Iterable<Member> memberIterable = repository.findAll();

        for (Member member : memberIterable) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        logger.info("DB Transaction failed: select: username does not exist in repository: " + username);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = getByUserName(username);
        //TODO
        return new User(member.getUsername(), member.getPassword(), Collections.emptyList());
    }
}
