# microservice01
this is springcloud
这篇springcloud包含了springcloud eureka的注册与发现

Eureka

Eureka是Netflix的一个子模块，也是核心模块之一。Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移。服务注册与发现对于微服务架构来说是非常重要的，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了。功能类似于dubbo的注册中心，比如Zookeeper。


原理

Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务注册和发现(请对比Zookeeper)。
 
Eureka 采用了 C-S 的设计架构。Eureka Server 作为服务注册功能的服务器，它是服务注册中心。
 
而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。SpringCloud 的一些其他模块（比如Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关的逻辑



三大角色
 
 Eureka server 提供服务的注册和发现
 
 Eureka provider 将自身服务注册到Eureka,从而使服务消费方能够找到
 
 Eureka consumer 从Eureka获取注册服务列表,从而消费服务
 
集群

- 处于不同节点的eureka通过Replicate进行数据同步 
- Application Service为服务提供者 
- Application Client为服务消费者 
- Make Remote Call完成一次服务调用
 
服务启动后向Eureka注册，Eureka Server会将注册信息向其他Eureka Server进行同步，当服务消费者要调用服务提供者，则向服务注册中心获取服务提供者地址，然后会将服务提供者地址缓存在本地，下次再调用时，则直接从本地缓存中取，完成一次调用。




ribbon的负载均衡和ribbon的自定义算法的实现,其中ribbon的7中算法包括
1 RoundRobinRule 轮询算法
2 RandomRule 随机算法
3 AvailabilityFilteringRule 会先过滤掉由于多次访问故障而处于断路跳闸状态的服务,还有并发的连接数量超过阈值的服务,然后对剩余的服务列表按轮询策略进性访问
4 WeightedResponseTimeRule 根据平均响应时间所有的服务的权重,响应时间越快服务权重越大被选中的概率就越大,刚启动如果统计信息不足时,会使用轮询策略,等信息足够会切换到
WeightedResponseTimeRule
5 RetryRule 先按照轮询获取服务,如果服务失败则在指定的时间内进行重试,获取可用的服务
6 RestAvaliableRule 先过滤掉由于多次访问故障而处于断路跳闸的服务,然后选择一个并发量小的服务
7 ZoneAvoidanceRule 默认规则,复合判断server所在区域的性能和server的可用选择服务器
负载均衡算法的切换,在configuration的bean方法下写IRule并return一个new 算法,可以是自己的
public class RandomRule_ZY extends AbstractLoadBalancerRule {

    private int total = 0;    //总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;//当前提供服务的机器号

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }


//            int index = rand.nextInt(serverCount);
//            server = upList.get(index);
            if(total < 5)
            {
                server = upList.get(currentIndex);
                total++;
            }else {
                total = 0;
                currentIndex++;
                if(currentIndex >= upList.size())
                {
                    currentIndex = 0;
                }

            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
 @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
Feign的负载均衡

    frign旨在WebService客户端,Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
  Feign是一个声明式的Web服务客户端，使得编写Web服务客户端变得非常容易，
  只需要创建一个接口，然后在上面添加注解即可。feign的负载均衡是自动的
  


服务熔断和服务降级


服务熔断好比保险丝,服务降级多在考虑宏观条件,

服务熔断
熔断机制是应对雪崩效应的一种微服务链路保护机制。
当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回"错误"的响应信息。当检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败就会启动熔断机制。熔断机制的注解是@HystrixCommand。


服务降级
整体资源快不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来。


服务监控(豪🐖) 指通过微服务组件进行可视化监控管理,能够很清楚哪个微服务的变化

zuul路由分配,能够命名统一的前缀和隐藏自己真实的路由地址

分布式配置中心,config模块的使用,能够将配置每个微服务的配置文件通过微服务的方式统一管理起来如github,svn



