# 自定义springboot-starter大致流程
- limit-stater：自定义的限流starter
- my-springboot：简单的springboot项目，在该项目中使用自定义starter

### 功能逻辑
该starter使用redis作为限流计数，通过注解的配置：过期时间内最大访问次数，通过切面将对应key的次数自增，并重新计算过期时间

### 自动配置
通过Springboot自动配置（SPI机制）将对应的配置放到`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`目录下
后续通过my-springboot导入之后，springboot自动配置会扫描该目录下的文件，并按照条件将对应的配置类加载到spring容器即可