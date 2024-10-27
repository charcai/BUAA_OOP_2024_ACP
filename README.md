# Academic Cloud Platform

![Language](https://img.shields.io/badge/language-java-brightgreen)
> 北京航空航天大学 软件学院 软件工程专业 
>
> 2321班 面向对象程序设计(OOP) 迭代作业

基于 `Java` 的教务云平台(`Academic Cloud Platform`)。

## 迭代1
> 开始时间：2024-10-10 19:00:00
>
> 结束时间：2024-11-01 23:59:00
>
> 补交截止：2025-01-18 23:59:00
> 
### 写在前面
同学们，从本周开始，我们将逐步开发一个教务云平台（Academic Cloud Platform）。我们会通过两次迭代，来不断完善系统功能。例如，ACP-1 阶段需要大家完成系统基本框架搭建，而 ACP-2 阶段则需要在 ACP-1 的基础上新增功能或修改已有功能。
在开始编写代码之前，建议大家先仔细阅读需求说明和文末的HINTS，再开始编写代码，编写好的代码需要经过 patpat 评测。不作弊不抄袭，不得盗取数据点。
1. 因为迭代一包含内容较多（迭代二会少很多），建议大家可以分两次完成，第一次完成命令1-5，第二次完成命令6-9。:laughing:
2. 建议大家在开始编写代码之前，好好想想该如何进行组织，怎样提高代码的可复用性，这样再进行开发时，如果遇到功能相同或类似的，则可以直接复用，减少冗余的代码。冗余的代码减少了，代码的可读性、一致性和可维护性也会随之上升。:laughing:
3. 如果你想让同学或者助教也拥有一个良好的DEBUG体验，请遵循第二条提示 :stuck_out_tongue_winking_eye:
不要以身试法，不要抱有侥幸心理，认为自己学术不端的行为不会被发现。:punch:
### 实验说明
#### 一、实验目标
1. 熟悉 Java 语法；了解 Java 项目的目录结构，了解包在Java中的使用。
2. 区分类变量与成员变量；区分类方法（静态方法）与成员方法；认识构造方法。
3. 学会适当封装，学习对象继承与多态的使用方式以及使用场景。
4. 学习使用方法的重载。
5. 学习面向对象的思想。
同学们一定要在开发的过程中，体会并学习 面向对象的思想， 不要认为这门课是Java语法课，或是仍然用面向过程的思想进行编程！！:thinking:
#### 二、实验的重难点
1. 逻辑处理。实验过程中需要判断不同情况，并匹配与之相对应的处理方案，同学们需要处理好这部分的逻辑。
2. 抽象。同学们需要将实验题目中涉及的实体、功能抽象为不同的类，不建议将大量的属性与功能塞到一个类中，不建议整个项目只有一个 Test 类。同时，建议将类型、功能相近的类归到一个包（可以认为是一个文件夹）下。
3. 成员方法、静态方法的使用场景。
4. 对于复杂的功能，思考如何将其拆分为合理的、相对简单的小功能，以便于实现。
5. 对于一个方法是定义在实体类中还是工具类中，一个功能直接通过一个方法实现还是将某些代码拆分出去之类的问题，可以从类的封装性以及代码的复用性两个方面进行考虑。合理的代码结构既可以减少 bug 的产生，也方便继续迭代修改，还能够提升代码的可读性。
#### 三、关键技术
- 输入与输出。
- 列表、哈希表、集合等类的使用。
- 集合类的排序方法。
#### 四、相关资料
详见文末
#### 五、DDL及其他说明
- 本次实验持续3周
- 注意输出是否有空格。一般情况下，如果没有特殊说明，每条输出均没有多余的空白符（如一条命令的开头没有空白符），同学们在复制粘贴时需要注意。

### 实验内容
#### 一、题目背景
随着教育事业和互联网技术的蓬勃发展，一款高效且便捷的教务云平台系统对于提高教学效率十分重要。这个教务云平台系统可以为师生们提供一个全面的学习与管理环境，使得他们能够方便地进行各种教学活动与学业管理。通过这个系统，教师可以高效地管理课程与学生，学生可以便捷地获取学习资源与参与各类学习活动。这款教务云平台系统的开发将进一步方便教学活动的进行，而这个任务将由你来完成！
#### 二、命令概览
本次迭代需要你完成如下命令：

| 命令符                             | 说明       |
|---------------------------------|----------|
| `quit`                          | 关机       |
| `register 学工号 姓名 密码 确认密码 身份类型`  | 用户注册     |
| `login 学工号 密码`                  |用户登录|
| `logout 学工号`                    |用户登出|
| `printInfo 可选参数`                |打印用户信息|
| `createCourse 课程名称 课程时间 学分 学时`  |创建课程|
| `listCourse 可选参数`               |查看课程|
| `selectCourse 课程号`              |选择课程|
| `cancelCourse 课程号`              |注销课程|

#### 三、功能描述
##### 1. 命令解析与开关机
**实现基本的命令解析，以及系统关闭功能。**
我们的命令以及参数**严格区分大小写**的。
首先，你的任务是编写一个 Test 类，当程序启动时，进入main方法，等待用户输入，输入命令的基本格式为

```
命令 [参数1] [参数2] [参数3] ... [参数n]
```

> 命令与参数、参数与参数的区分法则是：
    1. 一行内，出现的首个**不含空格**的字符串被视为命令，之后的若干个**不含空格**的字符串被视为参数。:thinking:
    2. 参数与命令、参数与参数之间，可能存在**多个空格**。如果“两个”参数之间没用空格，则这“两个”参数应视作一个参数。:thinking:
    3. 命令之前，最后一个参数之后的空格数量是**任意**的。:thinking:
          
###### 1.1 存在性分析
- 用户的每一行输入都**视为**一条完整（不一定正确）的命令
- 对于每一条命令，如果命令符未定义，那么请输出信息
```
Command '命令' not found
```
###### 1.2 参数数量合法性分析
- 对于每一条命令，失败时有且仅有一条错误信息输出
- 参数的数目是不定的（`n >= 0`）
- 如前文所述，以下命令在格式上是合法的：
```
command arg1 arg2	arg3     arg4
```
>   1. 我们的参数会直接给出，不存在**命令选项**（例如`gcc main.c -o main.exe`中的 `-o`）
>   2. **存在性分析和参数数量合法性分析是优先级第一和第二的操作，任何命令的解析都必须有这两步，如果并且（如果有的话）及时反映错误。详情信息可以查看文末的提示。**
>   3. 如果你对在Java中如何获取输入，如何进行输出尚有疑问，请查看文末的提示。

###### 1.3 系统退出
当终端输入 `quit` 时，系统退出（程序退出状态为`0`），**强制所有用户下线**，按照登录的先后顺序进行下线，**先登录的先被下线**此时应该在终端中输出`logout`的结果。例如，我们强制让学工号为`22371001`和`22371002`的用户下线，则应该先输出:
```
22371001 Bye~
22371002 Bye~
```
随后，在终端输出如下内容:
```
----- Good Bye! -----
```
> 1. quit 命令也需要检查参数数量是否合法。
> 2. 在迭代一中，并不会存在多个用户同时在线的情况。
##### 2. 用户注册
> 教务云平台提供三类身份：管理员、老师以及学生。在注册时，用户需要选择自己的身份。
###### 2.1 格式说明
|命令符|参数 1|参数 2|参数 3|参数 4|参数 5|
|:----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|`register`|学工号|姓名|密码|确认密码|申请身份类型|
###### 学工号
- 学号
    - 本科生： 学号为8位数字，从高向低数，最高两位代表入学年份，随后的两位代表学院编号，随后的一位代表大班号，最低3位代表班内编号。其中，本科生最多读6年，入学年份取值范围为[19, 24]，学院编号取值范围为[01, 43]，大班号取值为[1, 6]，最低三位没有特殊要求，取值范围为[001,999]。
    如，22375030代表22年入学，在序号为37的学院，第五大班，班内编号为30的同学学号。

	- 硕士生： 学号为9位数字，2位大写字母+7位数字。其中，`SY`开头代表学术型硕士研究生，`ZY`开头代表专业型硕士研究生，学院编号取值范围为[01, 43]，大班号取值为[1, 6]。硕士生最多读4年将被退学，所以硕士生的入学年份取值为[21,24] 。最低两位没有特殊要求，取值范围为[01,99]。

	- 博士生： 学号为9位数字，2位大写字母+7位数字。其中，`BY`开头代表博士研究生，博士生最多读6年，博士生的入学年份取值为[19,24]，学院编号取值范围为[01, 43]，大班号取值为[1, 6]，最低两位没有特殊要求，取值范围为[01,99]。如，SY2221118代表硕士研究生，22年入学，21系，第1大班。
- 教师号
对于教师而言，工号为5位数字，没有特殊限制，取值范围为[00001,99999]。
- 管理员号
对于管理员而言，工号为2位字母与3位数字，字母为`AD`，数字的取值范围为[001,999]

|类型|字母|1-2位数字|3-4位数字|5位数字|尾数|
|:-----:|:---:|:-----:|:------:|:-----:|:-----:|
|本科生|无|[19, 24]|[01, 43]|[1, 6]|[001, 999]|
|硕士生|SY/ZY|[21, 24]|[01, 43]|[1, 6]|[01, 99]|
|博士生|BY|[19, 24]|[01, 43]|[1, 6]|[01, 99]|
|教职工|无|特|殊|限|制|
|管理员|AD|仅|3|位|[001,999]|

###### 姓名
1. 由英文字母（大小写均可）和下划线两类组成，不要求两类字符至少出现一次。
2. 第一位不能为下划线。
3. 长度在 4-16 字符之间。

###### 密码
1. 长度为 6-16 位。
2. 由英文字母（大小写均可）、数字和特殊字符（@，_，%，$）三类构成，每类至少有一个字符。
          
###### 确认密码
- 两次输入的密码一致。
- 只需要判断确认密码和密码是否一致，不需要再判断确认密码的合法性。也就是说，密码的合法性判断优先级更高。
          
###### 身份类型
1. Administrator 管理员
2. Teacher 老师
3. Student 学生
> 为了简化，我们并不会出现学工号和身份类型均合法，但是身份不匹配的问题。:thinking:

###### 2.2 命令反馈说明
1. 注册成功
```
Register success
```
2. 参数数量不合法
```
Illegal argument count
```
3. 学工号不合法
```
Illegal user id
```
4. 用户已注册
```
User id exists
```
5. 姓名不合法
```
Illegal user name
```
6. 密码不合法
```
Illegal password
```
7. 确认密码与密码不一致
```
Passwords do not match
```
8. 身份类型不合法
```
Illegal identity
```
> 1. 上述的命令失败情况是按照优先级进行排序的，从上往下，优先级逐级递减。
> 2. 注意，命令的存在性判断依旧是最高的优先级。
> 3. 从某种意义上来说，命令执行成功的优先级最低。
> 4. 之后的命令也遵循上述的三条规则。

##### 3. 用户登录
> 对于已注册的用户，登录平台时需要输入学工号以及密码。
>
> 该系统不能够同时多次登录同一用户（但是可以**登录多个不同用户。迭代一中不需要考虑这种情形。**）
>
> **登录后，默认该用户为当前用户。**
###### 3.1 格式说明
|命令符|参数1|参数2|
|:---:|:---:|:---:|
|login|学工号|密码|
###### 3.2 命令反馈说明
1. 登录成功
```
Welcome to ACP, 学工号
```
其中学工号为登录的用户的学工号。
2. 参数数量不合法
```
Illegal argument count
```
3. 学工号不合法
```
Illegal user id
```
4. 用户未注册
```
User does not exist
```
5. 用户已登录
```
学工号 is online
```
其中学工号即为欲登录的用户的学工号。
6. 密码错误
无论密码格式是否正确，只要与注册时的密码**不一致**，均输出
```
Wrong password
```
> 1. 我们并不存在**指使某个用户**执行某个命令的情形。:thinking:
> 2. 需要注意的是，在我们(编写代码的各位同学)与该平台进行交互，也即输入一行行的命令的时候，**并不是作为真正的用户**,而是以一个**系统测试员**的身份去**模拟该平台的运行环境**，这也就是为什么我们的平台运行多人同时登录的原因，也是为什么会存在**当前用户**这一概念的原因。假设我们现在登录了某用户的账号，那么就默认当前用户是**被登录的用户**。所有除了`register`, `login`, `switch`以外的操作，都是以当前用户的名义发起的。在我们退出登录后，当前用户就默认为空，此时执行的任何非`register`, `login`, `switch`都是不合法的。:thinking:
##### 4. 用户登出
> 1. 用户进行登出的操作
> 2. 无参数时，**当前用户**登出。
> 3. 有参数时，**强制**学工号对应的用户登出。此举只能由 **`Administrator`** 执行， 即使学工号和当前在线用户的一致。
###### 4.1 格式说明
|命令符|可选参数|
|:---:|:---:|
|logout|[学工号]|
> 我们的迭代不会出现“信息未保存，是否强制登出"之类的问题。
###### 4.2 命令反馈说明
1. 登出成功
当系统有相应的在线用户时，输入该命令则（迫使在线用户）成功登出。
终端输出
```
学工号 Bye~
```
例如：
```
22373050 Bye~
```
2. 参数数量不合法
```
Illegal argument count
```
3. 当前无用户在线
```
No one is online
```
> TIPS：在迭代一中只会有一个用户在线，但是在迭代二中会涉及多人同时在线的问题，这里的“当前无用户在线”意思为当前用户未登录或者已登出，而非整个系统没有用户在线
        
###### 有参数
4. 权限不为 `Administrator`
```
Permission denied
```
5. 学工号不合法
```
Illegal user id
```
6. 用户不存在
```
User does not exist
```
7. 学工号对应的用户不在线
```
学工号 is not online
```
##### 5. 打印信息
> 1. 这个命令会打印使用**该平台用户的信息**。
> 2. 建议同学们重写 Object 类（这个类会被几乎所有Java中的类**默认继承**）的 toString() 方法以完成这项功能，当然也可以通过设计专门的格式化方法。
> 3. 无参数表示打印**当前用户的个人信息**，有参数则表示**打印他人信息**。
> 4. 教师和学生只可打印个人信息，管理员可打印任何人信息。为了方便，有参数时，**只有管理员**能够进行打印，对于学生和老师，即使学工号是自己的，**也没有权限**进行打印。
###### 5.1 格式说明
|命令符|可选参数|
|:--:|:--:|
|printInfo|[学工号]|
###### 5.2 命令反馈说明
1. 成功
输出信息应包括：学工号、姓名以及身份，并附上一行`Print information success`
```
User id: id
Name: username
Type: Administrator/Teacher/Student
Print information success
```
> 冒号为英文冒号，冒号后有一个**空格**
2. 参数数量不合法
```
Illegal argument count
```
> 1. 像这种可选参数的命令，参数数量的取值 **是一个范围**。 对于本命令，只要参数的个数在[0, 1]内，均是合法的。
> 2. 之后类似的命令也遵循上述规定。
3. 当前无用户在线
```
No one is online
```
###### 有参数
4. 权限不为`Administrator`
```
Permission denied
```
5. 学工号不合法
```
Illegal user id
```
6. 用户不存在
```
User does not exist
```
##### 6. 创建课程
> 1. **只有老师**可以创建课程，为保证教学质量，每个老师最多可注册 **10** 门课程。对于同一老师，课程名称**不可重复，创建该课程的老师即为该课程任课老师。同一个编号的课程不可能存在多个任课老师。**
> 2. 每门课程都有一个编号，编号格式为 C-数字，且**只能如此，否则均为非法情况（主要在选择课程时进行判断）。例如 `C-1`，`C-2`，数字无前导零。**课程编号是**全局**的：从 1 开始分配，若当前系统仅有 A、B 两个老师，A老师先注册了 2 门课程，那么当B老师注册第一门课程时，其课程编号分配为 `C-3`。特别地， `C-0` 是不合法的。
> 3. 注销课程的编号**不会再重新分配**。也就是说，你无需考虑“空闲”编号的情况。保证课程数量不会在 `Integer` 范围内溢出。
> 4. 注意创建课程的**时间冲突问题。**
###### 6.1 格式说明
|命令符|参数1|参数2|参数3|参数4|
|:---:|:---:|:---:|:---:|:---:|
|createCourse|课程名称|课程时间|学分|学时|

###### 课程名称
1. 由数字、英文字母、连接符-、下划线_组成。必须**要有英文字母，其他的是可选的。**
2. 第一位**必须为英文字母。**
3. 长度在 1～20 个字符之间。
4. **同一老师名下的所有课程名称不可重复，不同老师的课程名称可以重复。**
###### 课程时间
- 由`星期_上课时间`组成，其中星期由1-7的正整数表示，上课时间由X-Y表示（X，Y均为1-14之间的整数，此处默认一天有14节课）。注意到X必须小于等于Y。
- 如，java课在周二的第8-9节上课，那么课程时间就表示为**2_8-9**
- 课程时间冲突，也即在**同一天内，一门课和另一门课的上课时间存在重叠情况。**
###### 学分
- 学分为(0, 12]的数字（可以是整数，也可以是小数。 **输出时统一按小数处理，并保留一位小数**。 注意：不要默认小数为X.5）。在`Double`范围内，不会出现精度损失的情况。
###### 学时
- 学时为整数，取值范围为(0, 1280]。
###### 6.2 命令反馈说明
1. 创建课程成功
```
Create course success (courseId: C-X)
```
其中`X`为课程编号的数字。
2. 参数数量不合法
如果参数数量不为4，则输出：
```
Illegal argument count
```
3. 当前无用户在线
```
No one is online
```
4. 权限不为 `Teacher`
```
Permission denied
```
5. 拥有课程已达最大数
```
Course count reaches limit
```
6. 课程名称不合法
```
Illegal course name
```
7. 课程名称已存在
```
Course name exists
```
8. 课程时间不合法
```
Illegal course time
```
9. 课程时间冲突
```
Course time conflicts
```
10. 学分不合法
```
Illegal course credit
```
11. 学时不合法
```
Illegal course period
```
##### 7. 查看课程
###### 7.1 格式说明
> 1. 教学平台的用户可以使用此命令，来查看课程。不同的身份、不同的参数，会有不同的结果。
> 2. 无可选参数：学生和管理员查看所有课程，老师查看自己的所有课程，管理员查看所有课程。
> 3. 有可选参数：只有管理员查看号对应老师的所有课程。为简单起见，即使是自己的工号，老师也无权查看课程。

|命令符|可选参数|
|:--:|:--:|
|listCourse|[工号]|
###### 7.2 命令反馈说明
1. 成功打印信息
每门课程占一行，之后输出一行`List course success`
**老师**打印格式为 `课程号 课程名称 课程时间 学分 学时`，按课程编号**由小到大输出。**
```
# 举例
C-1 English_1 1_1-2 2.0 32
C-2 Al_1 2_1-2 2.0 32
List course success
```
**学生和管理员**打印课程时，格式为 `老师姓名 课程号 课程名称 课程时间 学分 学时`，先按老师姓名的**字典序，再按课程编号由小到大输出**。
```
# 举例
Lee C-1 Al_1 2_1-2 2.0 32
Lee C-2 QLBL 1_1-2 2.0 32
List course success
```
> 管理员的有参和无参打印的格式时相同的。
2. 参数数量不合法
```
Illegal argument count
```
3. 当前无用户在线
```
No one is online
```
###### 无参数
4. 课程不存在
对学生和管理员而言是**当前系统无课程**，对老师而言是**名下无课程**。
```
Course does not exist
```
###### 有参数
5. 权限不为`Administrator`
```
Permission denied
```
6. 学工号不合法
```
Illegal user id
```
7. 用户未注册
```
User does not exist
```
8. 工号对应的用户权限不为 `Teacher`
```
User id does not belong to a Teacher
```
9. 工号对应的老师名下无课程
```
Course does not exist
```
##### 8. 选择课程
> 1. **只有学生**可以使用该命令，选择一门课程。
> 2. 注意课程**时间冲突问题。**
> 3. 迭代一的测试样例中保证学生选择的课程**不会已满。**在迭代二中，我们强行规定，每一门课的上限为**30**人。

###### 8.1 格式说明
|命令符|参数1|
|:-:|:-:|
|selectCourse|课程号|

###### 8.2 命令反馈说明
1. 成功打印信息
```
Select course success (courseId: C-X)
```
2. 参数数量不合法
```
Illegal argument count
```
3. 当前无用户在线
```
No one is online
```
4. 权限不为 `Student`
```
Permission denied
```
5. 课程编号不合法
```
Illegal course id
```
6. 课程不存在或已注销
```
Course does not exist
```
7. 课程时间冲突
```
Course time conflicts
```
##### 9.注销课程
> 1. 老师和管理员可以通过这个命令，**注销某个课程。**
> 2. 学生可以通过这个命令，**取消对某个课程的选择。**
> 3. 老师可以注销**自己的课程。**
> 4. 管理员可以注销**任意课程。**
> 5. 学生选择与否，都 **不会影响（指老师和管理员进行注销时）** 注销课程的操作，但是你必须要维持学生选择的课程和现有课程之间的一致性。
###### 9.1 格式说明
|命令符|参数1|
|:-:|:-:|
|cancelCourse|课程号|

> 在老师和管理员注销课程时，应该同时维护学生已选课程的**一致性。**
###### 9.2 命令反馈说明
1. 注销课程成功
```
Cancel course success (courseId: C-X)
```
2. 参数数量不合法
```
Illegal argument count
```
3. 当前用户为空
```
No one is online
```
5. 课程号不合法
```
Illegal course id
```
6. 课程号不存在
对管理员而言，课程编号**未注册或已注销**；对老师和学生而言，课程编号**未注册或已注销或者不属于自己（未选择）。**
```
Course does not exist
```
#### 四、HINTS
##### 1. 命令优先级
首先，输入命令**未定义**时，输出命令不存在，替换下面的 `cmd` 为具体的命令名称。
```
Command 'cmd' not found
```
例如输入 `logged out`，由于命令 `logged` **未定义**（注意：这里的`out`作为参数而不是命令的一部分出现），所以输出
```
Command 'logged' not found
```
其次，当输入命令有定义但**参数个数不合法**时，输出
```
Illegal argument count
```
当命令有定义，参数个数正确时，才会进一步输出 `Already logged in`、`Bye ~` 等成功或失败信息。
当一句命令存在多种非法情况，按上述顺序，只输出**最先发生的非法信息。**
##### 2. 实现思路
这里的实现思路仅供参考，具体同学们可以自行发挥。:slightly_smiling_face:
###### 2.1 Java的输入与输出
一般我们会采用`Scanner`类处理Java的输入问题。如果我们的输入流是`System.in`（标准输入流），那么我们会这样实例化：
```java
Scanner s = new Scanner(System.in); // System.in可以被替换为其他的流
```
   一般我们使用`next()`, `nextLine()`来获取输入，并辅以`hasNext()`, `hasNextLine()`进行是否输入完成的判断。
```java
import java.util.Scanner;

public class Test{
public static void main(String[] args){
Scanner scan = new Scanner(System.in);

        // next()版
        while(scan.hasNext()){
            String str = scan.next();
            System.out.println("get data: " + str);
        }
        
        
        // nextLine()版
        while(scan.hasNextLine()){
            String str = scan.nextLine();
            //按照空格进行分割
            String[] strs = str.split("\\s+");
            for(String tstr : strs){
                System.out.println("get data: " + tstr);
            }
        }
        
        // 养成良好的编码习惯.
        scan.close();
    }
}
```
你可以尝试使用带参数的`hasNext()`.
输出十分简单，在我们的迭代中，`System.out.print()`或`System.out.println()`基本足够。
> 如果你想要更方便地进行本地的测试，将输入输出**重定向到文件中**也许是个不错的选择。在迭代一中，并不会对文件的重定向做强制性要求。:stuck_out_tongue_winking_eye:
> **绝对，绝对，绝对不要**在同一个流上，创建多个`Scanner`!!! :warning:
###### 2.2 实体类
以用户为例子。你可以设计一个 `User` 类（不限定类的名字，但一个好的名字是十分重要的），该类至少包括如下属性：
- 学号/教工号
- 姓名
- 密码
- 身份类型（可以通过一个字段表示，以此字段标识不同的身份）

*建议将属性设置为私有属性（`private`），并为其编写相应的 `getter`/`setter`*。

###### 2.3 工具类
对于一些具有**一定通用性**或者**不依附实体对象**的方法，例如检查卡号格式、检查密码格式、判断登录状态、判断注册状态、格式化输出实体类等，可以设计一些工具类，在其中实现这些方法，一般设计为静态方法即可。你可以认为，工具类是一个存储各种工具的类，这些工具就是方法，而这些工作是随时都可以被共享的。
> 如果某些同学提前接触过多线程编程，应该知道，有时候一个方法，一个属性，甚至一个类，都是可以被独享的。:astonished:
###### 2.4 全局变量管理
为了完成实验，同学们不免要设计一些全局变量。对于全局变量的管理，同学们可以设计一个类，相关的全局变量设置为 static；也可以设计一个类，而将全局变量设置为成员变量，之后通过**创建一个对象来操作全局变量。(Singleton模式）** 
一般来说，全局变量主要用于进行 **某些数据的临时存储**， 你可以认为管理全局变量的类是一个”仓库类“。
部分同学的大作业可能涉及到数据的持久存储。你可以使用**文件系统**或者**数据库**达成这个目的。:smiley:
###### 2.5 正则表达式
在判断姓名，密码是否合法时，建议大家用正则表达式来判断。例如，想要判断名字是否合法时，可以**参考**下列代码
```java
public boolean judgeName(String name) {
    String name_pattern = "[A-Za-z][A-Za-z_]{3,15}";
    if (!name.matches(name_pattern)) {
        return false;
    }
    return true;
}
```
正则表达式通常用于匹配字符串模式，不太适合处理范围验证，所以对于卡号的判断，可以分割字符串后再验证范围。正则表达式的更多用法，大家可以查看：[正则表达式 - 教程](https://www.runoob.com/regexp/regexp-tutorial.html)。
###### 2.6 List、Map 等的使用
在开发时， 合理使用`List`和`Map`加快我们的开发效率。
对于`List` ，常用的方式如下
```java
public class Test {
    public static void main(String[] args) {
        // List<YourClass> list = new LinkedList<>(); // 这个是链表（回忆一下数据结构）
        List<TargetClass> list = new ArrayList<>(); // 一般来说，用ArrayList<>()

        list.add(targetObject); // 在列表的尾部插入数据
        list.remove(index); // 根据下标删除元素。注意，index >= 0 && index < list.size()
    }
}
```
对于Map ，常用的方式如下
```java
public class Test {
    static class Person {
        public final String id;
        public String name;

        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        // Map<key, value> map = new HashMap<>();
        // Map<Key, value> map = new TreeMap<>();
        Map<String, Person> map = new HashMap<>();
        Person person = new Person("1234", "xiaoming");

        map.put(person.id, person);    // 向表中添加数据
        map.containsKey(person.id);    // 判断key是否存在
        Person p = map.get(person.id); // 根据key获取value
        map.remove(person.id);         // 删除key对应的value
    }
}
```
更多的使用方法同学们可以查阅相关资料，这里就不展开了。`List`和`Map`还涉及到“泛型”，感兴趣的同学可以提前了解了解。实际上，在我们给出的实际中，`List<TargetClass，Map<String, Person>`的尖括号的使用，其实就对应了泛型。
> 可以看到，在使用`List`和`Map`的时候，尖括号会传入一个类。因为Java面向对象的特性，基本数据类型（`int`,`double`,`boolean`）是不可以传入的，必须传入一个类。而每个基本数据类型都会对应一个类，例如`int`对应`Integer`，所以同学们在使用到`List`和`Map`，需要传入基本数据类型的时候，要记得把基本数据类型换为其对应的类。
###### 2.7 集合排序
```java
// users 是一个 HashMap<String, User>，其键为 user id
// getNumberUserId() 返回字符串 id 的数字部分
List<Map.Entry<String, User>> list
    = new ArrayList<>(users.entrySet());

Collections.sort(list, new Comparator<Map.Entry<String, User>>() {
    @Override
    public int compare(Map.Entry<String, User> u1,
                       Map.Entry<String, User> u2)
    {
        // 顺序排序，如果想要逆序，u2 - u1 即可
        return getNumberUserId(u1.getKey()) -
                getNumberUserId(u2.getKey());
    }
});

// 也可以用 lambda 表达式替换匿名类这种写法
// comparingInt 的参数是一个函数
// 这个函数接受一个泛型(这里是 Map.Entry<String, User>)，返回一个 int
// 该排序就根据 List 中每个元素返回的 int 值进行，默认为顺序
// 如果想要逆序，在返回值前添加一个负号即可

list.sort(Comparator.comparingInt(o -> getNumberUserId(o.getKey())));
```
##### 3 面向对象中的设计思想
这一板块将会介绍一些在进行面向对象编程时，比较好的设计思想。这部分的内容 也是**作为参考**， 同学们如果不采纳并不会造成任何负面影响（事实上，如果你只是单纯的抄袭，而没有加上自己的思考，代码会更不好编写）。此模块存在的意义，**实际上是想让同学们能够更深入地了解OOP，而不是只会编写Java代码，或者应付考试。**
###### 3.1 封装（Encapsulation）
在前文中，我们提到过关于实体类的设计。简单来说，实体类就是直接对应现实生活中存在的物品或者概念的类。一般而言，我们会遵循如下的方法设计一个实体类：
```java
public class Entity{
    private TYPE1 attribute1;
    private TYPE2 attribute2;
    //...
    public Entity(TYPE1 att1, TYPE2 att2, ...){
        this.attribute1 = att1;
        this.attribute2 = att2;
        //...
    }
    public TYPE1 getAttribute1(){
        return this.attribute1;
    }
    public void setAttribute1(TYPE1 att1){
        this.attribute1 = att1;
    }
    //...
}
```
> 如果你使用的是IDEA作为IDE，那么可以按下`alt+Insert`，便可以选择自动生成各种`getter()`, `setter()`, `toString()`, `equals()`以及构造方法。

为什么要这么做呢？实际上，对于一个**小项目**来说，你确实大可不必降属性封装为`private`并且设计相应的`getter()`,`setter()`，这样在一定程度上会给你的编码**带来便利**，尤其是当你设置其访问控制权限为`public`时，你可以在同项目下的任何一个文件，任何一个类中访问并使用那个属性，岂不快哉？

但是，随着类的数量的增多，这样做的麻烦逐渐显现：
1. 类与类之间无封装可言，大家都是**透明**的，可以**肆意访问并修改**各自的属性，这就会导致混乱，而且对属性的访问和修改也不是很明显（比较`a = 1` , `setA(1)`)，这会增大DEBUG难度
2. 一般来说，我们只会提供能够完成一个方法的**最少但是必要的信息**。**例如，现在有一个方法需要知道当前有多少用户（假定有一个List用于存储用户），那么我们只需要传入这个`List`的长度，而不需要将整个`List`都传入，然后再求长度。一方面，这样可能会造成不必要的内存开销（值传递和引用传递的问题，虽然在Java中这类问题基本不存在）**，另一方面，传入`List`后，这个方法就可以**修改其内容**，这可能会导致不必要的麻烦
3. 难以进行**合法性判断。如果在访问和修改某个属性时，需要考虑到类似于权限和值的合法性问题，**我们总是会写一个专门的合法性判断方法，甚至创造一个合法性判断类。但是这显然是没必要的，一个类的属性的合法性判断工作没有任何理由交给其他的类担任，相关的工作我们完全可以在`setter()`,`getter()`中胜任。以下给出一个很简单的例子：
```java
public Exam{
    private Integer grade;
    public Integer getter(){
        return this.grade;
    }
    public void setter(String person, Integer grade){
        if(person.equals("Teacher") && grade >= 0 && grade <= 100){
            this.grade = grade;
        }
    }
}
```
> 1. setter()方法的返回值不一定非要是`void`，你可以用返回值来表示执行的状态，发生了什么错误等。:astonished:
> 2. 另外一个表示发生的错误类型的方法是异常（Exception）。:full_moon_with_face:
> 3.  同学们进入大二下，学习数据管理技术后，就更能深刻地理解这样设计实体类的意义了. :smile:

###### 3.2 继承（Inheritance）
为什么要继承？简单来说，就是降低代码**重复度**，并且提供**统一的接口**。实际上，这两个功能是相互绑定的。例如：
```java
//Person.java
public class Person{ //平行四边形
    protected String name;
    protected String getName(){
        return "I am " + name;
    }
}

//Teacher.java
public class Teacher extends Person{
    @Override
    protected String getName(){
        "I am " + name + ", a teacher";
    }
}

//Student.java
public class Student extends Person{
;
}
```
`Person`, `Teacher`, `Student`类都含有`String name`的属性，因此重复写三个`String name`是不必要的。这三个类也都具有`getName()`的方法，`Student`的和`Person`保持一致，而`Teacher`的则进行了一些改进。因为具有相同的属性名和方法名，当别人需要接手你的项目时，需要阅读并理解的方法数量变减少了，这将更有利于你们的协作。

因为继承在先前的实验中也进行了详细的说明，在此就不过多赘述。

###### 3.3 多态（polymorphism）
多态是面向对象里面的大杀器。某种程度上，是否是一个优秀的面向对象编程的程序员，就在于你是否熟练掌握了多态。接下来，我们将会给出一种新的设计思路，该设计实现思路其实和设计模式中的`State`模式比较类似。**在这种方法下，我们便可以简化逻辑，让我们的程序更加模块化，减少DEBUG的难度。**
> 1. 此处给出的样例是为了体现面向对象编程中的 设计思想， 并不推荐直接在迭代中使用。:thinking:
> 2. 如果你想要使用，应该进行一些深入的思考。否则只会越用越乱。 :thinking:

首先我们可以定义`User`类为抽象类，其有一个抽象方法`execute()`：
```java
public abstract class User{
    //其他属性，此处略去
    public abstract void execute();
}
```
然后再分别定义`Administrator`, `Student`和`Teacher`类，继承`User`类并且实现`execute()`方法：
```java
public class Administrator extends User{
    @Override
    public void execute() {
        System.out.println("I am administrator!");
    }
}

public class Student extends User{
    @Override
    public void execute() {
        System.out.println("I am student!");
    }
}

public class Teacher extends User{
    @Override
    public void execute() {
        System.out.println("I am teacher!");
    }
}
```
这里只是做了一个简单的实现示例。在我们的场景中，execute()方法很明显 应该与命令相关， 你可以将命令作为参数传入，也可以在User类中创建一个 **命令的容器**（这个方法主要可以用来进行命令的撤回，但是我们的项目并不涉及） 作为其成员属性，在执行execute()的时候只需要从该容器中取出相应的命令便可。

> 事实上，你还可以使用 **Command模式** 来解决这一类问题。另一方面，**State模式** 其实和**Strategy模式**比较类似，同学们也可以多做了解。:smile:

为什么要这样实现呢？其实主要是为了防止 **硬编码（直接将具体的值，如字符串、数字、路径等，写入源代码中。你可以将这里的具体的值理解为一个函数）** 导致的难以DEBUG的问题。试想如下的代码：
```java
//未使用继承版本:
void handleCommand(){
    User user; // user代表当前用户, user.status代表身份
    if(user.status == "Administrator"){
        // handle command...
    } else if(user.status == "Student"){
        // handle command...
    } else if(user.stauts == "Teacher"){
    // handle command...
}
}

//仅把继承作为提取公共属性和方法使用:
void handleCommand(){
    User user; //user代表当前用户
    if(user instanceof Administrator){
        //handle command...
    } else if(user instanceof Student){
        //handle command...
    } else if(user instanceof Teacher){
        //handle command...
    }
}
```
一方面，如果我们有很多种`User`，每种`User`对应一个相同的命令都有不同的解析策略，如果这个策略很复杂，每个`if`块中就会有若干行代码,更可怕的是，如果这其中还有`if`分支，就会更加难以阅读。而且，当我们需要修改相应的处理方式时，很有可能因此犯错。

>例如下面的代码 （检测平均分是否正确，并且进行学生成绩、评价和学生的配对。这并不是我们迭代的功能，只是为了示范） ：
```java
public class DEMO{
    public void judge(Integer flag,String message,List<Integer> grades, > Double average, List<String> comments){
        if(a == 1){
            if(message.isEmpty()){
                System.out.println("Message is empty.");
            } else{
                int count1 = grades.size();
                if(count == 0){
                    System.out.println("Grade list is empty.");
                } else {
                    int count2 = comments.size();
                    if(count2 != count1){
                        System.out.println("The number of comments > doesn't equals to the number of grades.");
                    } else{
                        double sum = 0;
                        for(Integer grade : grades){
                            sum += grade;
                        }
                        if(sum / count1 != average){
                            System.out.println("The average is wrong.");
                        } else{
                            matchGrade(grades, comments, average); // 进行成绩和评价的匹配
                        }
                    }
                }
            }
        } else if(a == 2){
            //...
        }
    }
}
```
>如果行数继续增加，**代码可读性将会大幅度下降**。即使是代码编写者，长时间不管理这部分的代码，之后也需要花费一定的时间来进行理解。

很显然，没有人喜欢阅读一个**几百行的``if``嵌套版块**。

但是，我们如果采取了提示的写法，那么整个逻辑就变成了：
```java
void handleCommand(){
    User user;
        user.execute();
}
```
如果某一种身份（权限）处理命令是出现了BUG，我们也只是需要去到相应的实现部分对代码进行修复便可。

简单来说，其实就是将具体的执行逻辑和算法放在其他的类里面（类似于**工具类**），而不是全部堆在一个类里面。
###### 3.4 真正的State模式
> 由于我们的迭代中，每个命令的效果其实相差不大，因而使用`State`模式其实不是很合适。

进一步分析，我们发现，在`User`类里面添加的`execute()`方法似乎很奇怪，因为真正执行命令的，实际上是我们的系统，这样破坏了**封装性和模块性**

另外，可以看到，其实主要影响我们执行命令的，无非就是当前用户的身份（权限）。这其实就是一个状态，而我们执行命令的方式其实就和这个状态有关，所以我们完全可以把执行命令的方法**封装在一个`State`类里面**。这样，我们还是按照先前实体类的设计方式来设计`Student`, `Teacher`, `Administrator`类，而采用一个`Executor`类来执行若干命令。同时，我们有`StuState`, `TeacherState`, `AdminState`来分别代表三种权限下的状态。
```java
//State.java
public abstract class State{
    public abstract void execute();
}

//StuState.java, TeacherState, AdminState类似
public class StuState extends State{
    @Override
    public void execute(){
        System.out.println("I am a student.");
    }
}

//Executor.java
public class Executor{
    private State state; //表明当前的状态
    public void handleCommand(){
        state.execute();
    }
}
```
这样，根据不同的`State`，我们就可以调用不同的`execute()`函数，执行在3种权限下，对命令的解析。
> 1. 你应该考虑`state`的切换问题。
>
> 2. 一般地，我们会传入一个参数以表示解析执行的是哪个命令，但在上述实现中省略了这一点
>
> 3. 细心的同学可能已经发现，`StuState`, `TeacherState`, `AdminState`可以使用`Singleton`模式（单例模式）。

#### 五、测试样例
##### 命令1-5
###### 输入
```
registre
logiin
loggout
ppprintInfo
Printinfo
register 22370000
register 22370001
register 22370001 _MJ
register 22371001 Mu_Jue Aa123@ Aa123@ Student
register 22371001 Mu_Jue Aa123@ Aa123@ Student
register AD001 ad_min Bb123$ Bb123$ Administrator
login 223700011
login 22371001 Aa123@
login 22371001 Aa123@
printInfo
logout 22371001
logout
login AD001 Bb123$
printInfo 22371001
quit
```
###### 输出
```
Command 'registre' not found
Command 'logiin' not found
Command 'loggout' not found
Command 'ppprintInfo' not found
Command 'Printinfo' not found
Illegal argument count
Illegal argument count
Illegal argument count
Register success
User id exists
Register success
Illegal argument count
Welcome to ACP, 22371001
22371001 is online
User id: 22371001
Name: Mu_Jue
Type: Student
Print information success
Permission denied
22371001 Bye~
Welcome to ACP, AD001
User id: 22371001
Name: Mu_Jue
Type: Student
Print information success
AD001 Bye~
----- Good Bye! -----
```
##### 命令6-9
###### 输入
```
register 22373001 Mu_Jue Aa123@ Aa123@ Student
register 00001 T_ea Bb123$ Bb123$ Teacher
login 22373001 Aa123@
createCourse OO 4_3-4 3.0 48
logout
login 00001 Bb123$
createCourse 8*&&@!
createCourse _OO 4_3-4 3.0 48
createCourse A*XXXX 1_2-3 4.5 72
createCourse OO 4_3-4 3.0 48
createCourse CO 5_3-4 4.5 72
listCourse
logout
login 22373001 Aa123@
listCourse
selectCourse C-0
selectCourse C-2222222
selectCourse C-1
selectCourse C-2
logout
login 00001 Bb123$
cancelCourse C-0
cancelCourse C-1
listCourse
logout
login 22373001 Aa123@
listCourse
quit
```
###### 输出
```
Register success
Register success
Welcome to ACP, 22373001
Permission denied
22373001 Bye~
Welcome to ACP, 00001
Illegal argument count
Illegal course name
Illegal course name
Create course success (courseId: C-1)
Create course success (courseId: C-2)
C-1 OO 4_3-4 3.0 48
C-2 CO 5_3-4 4.5 72
List course success
00001 Bye~
Welcome to ACP, 22373001
T_ea C-1 OO 4_3-4 3.0 48
T_ea C-2 CO 5_3-4 4.5 72
List course success
Illegal course id
Course does not exist
Select course success (courseId: C-1)
Select course success (courseId: C-2)
22373001 Bye~
Welcome to ACP, 00001
Illegal course id
Cancel course success (courseId: C-1)
C-2 CO 5_3-4 4.5 72
List course success
00001 Bye~
Welcome to ACP, 22373001
T_ea C-2 CO 5_3-4 4.5 72
List course success
22373001 Bye~
----- Good Bye! -----
```