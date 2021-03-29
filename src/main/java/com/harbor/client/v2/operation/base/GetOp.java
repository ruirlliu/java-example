package com.harbor.client.v2.operation.base;

/**
 * @author lr
 * @date 2021/2/4
 */
public interface GetOp<E> extends BaseOp<E>{

    E get();

}
