package nong.soon.bae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.UsersRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
	private final UsersRepository usersRepository;

    public void save(UsersDTO users) {
        validateDuplicateMember(users);
        usersRepository.save(users);
    }

    private void validateDuplicateMember(UsersDTO users) {
    	UsersDTO findMember = usersRepository.FindByEmail(users.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    
    public boolean isUsernameAvailable(String username) {
        UsersDTO existingUser = usersRepository.FindByUser(username);
        return existingUser == null;
    }
}
