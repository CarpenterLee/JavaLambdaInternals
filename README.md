# Java Functional Programming Internals

# Authors

| Name | Weibo Id | Blog | Mail |
|:-----------|:-------------|:-------------|:-----------|
| 李豪 |[@计算所的小鼠标](http://weibo.com/icttinymouse) | [CarpenterLee](http://www.cnblogs.com/CarpenterLee/) | hooleeucas@163.com |

# Introduction

本文受启发于[JavaOne 2016](https://www.oracle.com/javaone/index.html)关于*Lambda表达式*的相关主题演讲[*Lambdas and Functiona*l Programming](https://blogs.oracle.com/thejavatutorials/entry/learn_java_8_lambdas_and)和[*Refactoring to Java 8*](https://blogs.oracle.com/thejavatutorials/entry/javaone_2016_refactoring_your_code)。

Java 8已经发行两年多，但很多人仍然在使用JDK7。对企业来说，技术上谨慎未必是坏事，但对个人学习而言，不去学习新技术就很可能被技术抛弃。Java 8一个重要的变更是引入**函数式编程**和**Lambda表达式**(`lambda expression`)，这听起来似乎很牛，有种我虽然不知道Lambda表达式是什么，但我仍然觉得很厉害的感觉。

**Java stream包**是跟*Lambda表达式*同时添加新功能。**Lambda表达式只有跟stream一起使用才能显示其真实的威力**。

本系列文章不打算去争论“*什么才算是一门真正的函数式语言*”这类问题。我们会将**重点放在如何使用Java Lambda表达式，如何使用stream，以及二者背后的原理**。

# Contents

具体内容安排如下：

1. [Lambda and Anonymous Classes(I)](./1-Lambda%20and%20Anonymous%20Classes(I).md)，展示如何使用Lambda表达式替代匿名内部类，说明Lambda表达式和函数接口的关系。
2. [Lambda and Anonymous Classes(II)](./2-Lambda%20and%20Anonymous%20Classes(II).md)，Lambda表达式的实现原理
3. [Lambda and Collections](./3-Lambda%20and%20Collections.md)，学习Java集合框架（*Java Collections Framework*）新加入的方法
4. [Streams API(I)](./4-Streams%20API(I).md)，Stream API基本用法
5. [Streams API(II)](./5-Streams%20API(II).md)，Stream规约操作用法，顺道说明接口静态方法和默认方法以及方法引用的概念。
6. [Stream Pipelines](./6-Stream%20Pipelines.md)，Stream流水线的实现原理
7. Stream并行实现原理（待写，>>欢迎感兴趣的同学完善<<）
8. [Stream Performance](./8-Stream%20Performance.md)，Stream API性能评测




