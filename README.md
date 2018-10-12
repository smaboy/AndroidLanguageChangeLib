# AndroidLanguageChangeLib

### Android语言切换库

####  该项目实现的功能:

  - 在，app内有语言自动识别，个性设置（如：简体中文、英文等）等需求
  描述如下:

  1. 在选择自动情况下，能根据系统语言来实现app语言切换
  2. 在选择语言的情况下，如英文，app内的语言能切换为英文（系统语言为其他，不会
  改变这个设定，在app重启等情况下，app内语言应和用户设置的保持一致，只有在用户
  又重新设定的情况下，才改变）

  - 实现方案：

  1. 采用网络设置，该设置的原理在于，该app内所有的展示文字，及英文版需要的内容全部
  由服务器提供；
  2. 本地写死的内容采用在资源文件设置对应的不同资源实现

  - 难点分析
  1. 如果采用本地，提供展示文字方案，需要解决无非两点，一是在xml文件中直接赋值，二是在代码中
  手动赋值。如下为，更新app语言的代码，我们需要注意的是这个context上下文，不能为application
  中的上下文，如果为application的上下文，我们xml中赋值的代码将不能同步。

>       public static void changeAppLanguage(Locale locale, Context context) {
>
>          //适配8.0
>          Resources resources = context.getResources();
>         DisplayMetrics dm = resources.getDisplayMetrics();
>          Configuration config = resources.getConfiguration();
>          config.locale = locale;
>          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
>              LocaleList localeList = new LocaleList(locale);
>              LocaleList.setDefault(localeList);
>              config.setLocales(localeList);
>              context.getApplicationContext().createConfigurationContext(config);
>              Locale.setDefault(locale);
>          }
>          resources.updateConfiguration(config, dm);
>       }

   2. 用户语言选择的持久化问题

             解决方案：

                 当用户进入语言切换页面，并选中了相应的语言，这是我们将其保存下来，我这里采用的是sp保存，因为内容
             少且适合键值对保存。


   3. 用户切换语言，会导致我们获取系统语言的不准性问题

          解决方案：

              我们应该在应用的初始化时，就获取系统语言，并将其保存下来，我这里采用的是sp保存，因为内容
          少且适合键值对保存。再次，设置广播监听，当系统语言发生变化时，我们将其获取并保存，也可做其他处理
          的。

#### 效果图如下

  <img src="demo.gif">


#### 参考项目
  git地址: https://github.com/MichaelJokAr/MultiLanguages

  博客地址: https://blog.csdn.net/a1018875550/article/details/79845949





