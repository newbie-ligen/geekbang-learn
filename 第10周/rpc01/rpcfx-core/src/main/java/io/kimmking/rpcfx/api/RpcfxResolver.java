package io.kimmking.rpcfx.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass,String group,String version);

}
