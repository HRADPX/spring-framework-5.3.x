package com.hr.tx;

import com.hr.mode.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-16
 */
@Service
public class UserProgrammaticService {

	@Autowired
	private UserMapper userMapper;

	private final TransactionTemplate transactionTemplate;

	public UserProgrammaticService(PlatformTransactionManager transactionManager) {
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public List<User> execute(List<Integer> userIdList) {
		if (CollectionUtils.isEmpty(userIdList)) {
			return Collections.emptyList();
		}
		// maybe do other thing....
//		return transactionTemplate.execute(status -> userMapper.queryByIds(userIdList));
		return transactionTemplate.execute(status -> {
			try {
				return userMapper.queryByIds(userIdList);
			} catch (Exception e) {
				// 默认只对 RuntimeException 和 Error 回滚，如果要自定义回滚异常，
				// 可以手动调用 status.setRollbackOnly()
				status.setRollbackOnly();
				return Collections.emptyList();
			}
		});
	}
}
