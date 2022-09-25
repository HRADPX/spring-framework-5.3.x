package com.hr.tx;

import java.util.Collection;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-03-30
 */
public interface BaseService<T> {

	void execute(T t);

	void batchExecute(Collection<T> list);
}
