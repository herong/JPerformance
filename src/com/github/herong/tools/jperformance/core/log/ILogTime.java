/**
 * 
 */
package com.github.herong.tools.jperformance.core.log;

/**
 * TODO 这里用文字描述这个类的主要作用
 * @author herong
 * @createTime 2012-2-23 下午02:57:18
 * @modifier 
 * @modifyDescription 描述本次修改内容
 * @see
 */

public interface ILogTime<T> {
    public T run() throws Exception;
}
