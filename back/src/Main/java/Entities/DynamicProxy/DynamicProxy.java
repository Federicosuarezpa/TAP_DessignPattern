package Entities.DynamicProxy;

import Entities.ActorProxy.ActorProxy;
import Entities.InsultService.InsultServiceInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private Object insultService;

    private static final DynamicProxy dynamicProxy = new DynamicProxy();

    private DynamicProxy() {}

    /**
     *
     * @return DynamicProxy singleton entity
     */
    public static DynamicProxy getInstance(){
        return dynamicProxy;
    }

    /**
     *
     * @param insultService
     * @param proxy
     * @return InsultServiceInterface to intercept all the methods
     * @throws Throwable
     */
    public InsultServiceInterface intercept(Object insultService, ActorProxy proxy) throws Throwable {
        this.insultService = insultService;
        InsultServiceInterface od = (InsultServiceInterface) Proxy.newProxyInstance(
                ActorProxy.class.getClassLoader(),
                new Class[]{InsultServiceInterface.class}, dynamicProxy);
        return od;
    }

    /**
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if(Object.class  == method.getDeclaringClass()) {
            String name = method.getName();
            if("equals".equals(name)) {
                return proxy == args[0];
            } else if("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        return method.invoke(insultService, args);
    }
}
