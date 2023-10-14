package com.hr.tx;

import com.hr.mode.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-01-19
 *
 * (1) execute 方法添加 @Transactional 注解
 * 		两条 sql 语句都回滚.
 * (2) updateUser 方法添加 @Transactional 注解，execute 方法没有添加 @Transactional 注解
 * 		两条 sql 语句都不回滚.
 * 	如果要回滚被调方法的 sql 执行，
 * 		第一种方式，向 UserTxService 中注入 UserTxService 属性
 * 		第二种方式，使用代理对象
 * 	注意：即使如此，第二条更新 sql 发生异常的第一条 sql 也不会回滚，因为它没有事务注解
 * 	(3) execute 和 updateUser 两个方法都添加了 @Transactional 注解，如果在 execute 方法中将
 * 	updateUser 的异常 catch 捕获，则分两种情况（execute 事务使用默认的传播方式）：
 * 		1) 如果 updateUser 方法的事务传播方式为 REQUIRED，表示在原先已有的事务中执行，此时发生异常
 * 	两条 sql 都会回滚，因为使用的是同一个事务，即使用的是同一条数据库连接。
 * 		2) 如果 updateUser 方法的事务传播方式为 REQUIRED_NEW，表示开启一个新的事务执行该方法，此时
 * 	发生异常，updateUser 对数据库的修改后回滚，由于抛出的异常在 execute 方法中被捕获，execute 正常
 * 	执行完成后，会提交事务成功，对数据库的修改不会回滚，因为此时是两个事务，即两条数据库连接。
 * 		3) 如果 updateUser 方法的事务传播方式为 NESTED，表示使用嵌套事务，两个操作使用的还是使用的
 *  同一个事务，但是会使用保存点控制事务的提交和回滚，结果和第二种相同.
 *  // there has transaction operation...
 * 	try {
 * 		((UserTxService) AopContext.currentProxy()).updateUser(user, user.isException());
 * 	} catch (Exception e) {
 * 		log.info("catching exception, type: {}", e.getClass().getSimpleName());
 * 	}
 *
 */
@Service
public class UserTxService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;


	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	private void updateUser(User user, boolean isException) {
//		applicationEventPublisher.publishEvent(new TransactionUserEvent(user, user.getId()));
		userMapper.updateModel(user, isException);
	}

	@Transactional(rollbackFor = Exception.class)
	public void execute(List<User> userList) {
		if (CollectionUtils.isEmpty(userList)) {
			return;
		}
		userList.forEach(user -> {
			userMapper.insertModer(user);
//			updateUser(user, user.isException());
//			((UserTxService) AopContext.currentProxy()).updateUser(user, user.isException());
			try {
				((UserTxService) AopContext.currentProxy()).updateUser(user, user.isException());
			} catch (Exception e) {
				System.out.println("catching exception........." + e);
			}
			// 事务完成提交后发布一个事件
//			applicationEventPublisher.publishEvent(new TransactionUserEvent(user, user.getId()));
		});

	}

	public List<User> queryByIds(Collection<Integer> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return userMapper.queryByIds(ids);
	}

	@Transactional
	public void execute0(List<User> userList) throws IOException{
		if (CollectionUtils.isEmpty(userList)) {
			return;
		}
		for (User user : userList) {
			try {
				userMapper.insertModer(user);
				throw new IOException("not open any file...");
			} catch (Exception e) {
				System.out.println("exception catching, the type of the exception: " + e.getClass().getName());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
	}
}
