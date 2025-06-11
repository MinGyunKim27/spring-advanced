package org.example.expert.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.global.common.dto.AuthUser;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.example.expert.domain.manager.dto.response.ManagerResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.manager.repository.ManagerRepository;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.example.expert.global.common.exception.domain.manage.ManagerNotFoundException;
import org.example.expert.global.common.exception.domain.manage.SelfAssignNotAllowedException;
import org.example.expert.global.common.exception.domain.todo.TodoNotFoundException;
import org.example.expert.global.common.exception.domain.todo.TodoOwnerMismatchException;
import org.example.expert.global.common.exception.domain.todo.TodoUserMissingException;
import org.example.expert.global.common.exception.domain.user.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public ManagerSaveResponse saveManager(AuthUser authUser, long todoId, ManagerSaveRequest managerSaveRequest) {
        // 일정을 만든 유저
        User user = User.fromAuthUser(authUser);
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);

        if (todo.getUser() == null){
            throw new TodoUserMissingException();
        }

        if (!ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId())) {
            throw new TodoOwnerMismatchException();
        }

        User managerUser = userRepository.findById(managerSaveRequest.getManagerUserId())
                .orElseThrow(ManagerNotFoundException::new);

        if (ObjectUtils.nullSafeEquals(user.getId(), managerUser.getId())) {
            throw new SelfAssignNotAllowedException();
        }

        Manager newManagerUser = new Manager(managerUser, todo);
        Manager savedManagerUser = managerRepository.save(newManagerUser);

        return new ManagerSaveResponse(
                savedManagerUser.getId(),
                new UserResponse(managerUser.getId(), managerUser.getEmail())
        );
    }

    @Transactional(readOnly = true)
    public List<ManagerResponse> getManagers(long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);

        List<Manager> managerList = managerRepository.findByTodoIdWithUser(todo.getId());

        List<ManagerResponse> dtoList = new ArrayList<>();
        for (Manager manager : managerList) {
            User user = manager.getUser();
            dtoList.add(new ManagerResponse(
                    manager.getId(),
                    new UserResponse(user.getId(), user.getEmail())
            ));
        }
        return dtoList;
    }

    @Transactional
    public void deleteManager(long userId, long todoId, long managerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);

        if (todo.getUser() == null){
            throw new TodoUserMissingException();
        }

        if (!ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId())) {
            throw new TodoOwnerMismatchException();
        }

        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(ManagerNotFoundException::new);

        if (!ObjectUtils.nullSafeEquals(todo.getId(), manager.getTodo().getId())) {
            throw new TodoOwnerMismatchException();
        }

        managerRepository.delete(manager);
    }
}
