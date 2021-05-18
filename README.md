# 玖言国学起名系统

仅限内部使用

## 使用技术

- Spring Boot
- Vue + Vuetify
- FreeMarker
- Mybatis

## 页面统计

共10个页面，7个弹窗

+ 字库上传
    + 拼音选择弹窗

+ 字库管理
    + 编辑弹窗

+ 名库上传
    + 拼音选择弹窗

+ 名库管理
    + 编辑弹窗

+ 添加订单
    + 五行选择弹窗

+ 订单列表
    + 调整弹窗
    + 完成弹窗

+ 回访对象录入

+ 应回访列表

+ 待交付列表

+ 执行页面

## API接口统计

共37个API接口

+ 字库控制器 8个

+ 名库控制器 8个

+ 订单控制器 22个

+ 回访用户控制器 5个

## 数据库

+ 字库表
    + 11列

+ 名库表
    + 10列

+ 订单表
    + 24列

+ 订单调整表
    + 4列

+ 订单生成名表
    + 8列

+ 订单命盘表
    + 17列

+ 订单命局表
    + 14列

+ 回访用户表
    + 7列

## SQL Mapper

+ 字库模块
    + 12个

+ 名库模块
    + 14个

+ 订单模块
    + 21个

+ 回访用户模块
    + 4个

## 非常规点

+ 字库管理
    + 无音标拼音处理（难度★✰✰✰✰）

      为了禁用拼音判断的需求，需要保存不带音调的拼音。做一个映射，将有带音调的拼音映射成没有音调的拼音并保存。

+ 名库管理

  + 同上

+ 订单管理
    + 生成名字（难度★★★✰✰）

      生成名字功能需要考虑的问题有完整覆盖分支，减少数据库频繁读写，加快生成速度，高优先级名取完判断等等。

    + API请求（难度★✰✰✰✰）
      项目使用了两个API服务商提供的API服务，阿里云API与百度云API的请求方式和返回数据的差异性存在。
      阿里云的API返回的数据只有一层，读出的数据需要使用映射表进行映射翻译。
      百度云API返回的是树形结构数据，层级较多，拿指定数据较为麻烦。

    + 执行列表页面排版（难度★✰✰✰✰）

      整体排版与文字一键复制功能

## 代码统计

| 语言                | 文件 | 代码行数 | 注释行数 | 空白行数 |
| ------------------- | ---- | -------- | -------- | -------- |
| Java                | 63   | 2428     | 180      | 545      |
| Freemarker Template | 16   | 3581     | 0        | 34       |
| XML                 | 18   | 1534     | 0        | 60       |
| HTML                | 3    | 370      | 0        | 2        |
| CSS                 | 3    | 220      | 0        | 50       |
| JavaScript          | 1    | 148      | 1        | 45       |
| Bourne Shell        | 2    | 135      | 45       | 27       |
| Markdown            | 2    | 119      | 0        | 61       |
| DOS Batch           | 1    | 66       | 2        | 21       |
| Gradle              | 2    | 36       | 5        | 5        |
| YAML                | 2    | 22       | 3        | 3        |
| 总数                | 113  | 8659     | 236      | 853      |

## 定价

| 模块         | 价格 |
| ------------ | ---- |
| 字库管理     | 1900 |
| 名库管理     | 2000 |
| 订单管理     | 5400 |
| 回访用户管理  | 900  |
| 总共        | 9200 |

## 项目总结

整体项目的需求在前期基本上有做了比较详尽的思维导图，目标较为明确，整体思路顺畅。
中期出现的需求变更：

1. 单字上传逻辑修改，原需求是批量添加，后修改为单字添加实时修改。
2. 字库管理与名库管理已添加名显示。
3. 添加寓意起名订单，动态添加五行输入
4. 订单列表本操作栏从“调整”“修改”修改为“调整”“删除”“完成”
