package com.harbor.client.v2.operation.exec;

/**
 * @author lr
 * @date 2021/2/4
 */
public interface Exec<CMD extends ExecCmd<RES>, RES> {

    RES exec(CMD cmd);
    
}
