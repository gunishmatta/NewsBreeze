The News Breeze App is made using MVVM architecture pattern 

The folders are basically named according to the layers
1. data-data layer which contains models,repository along with local and remote data sources, local-Room DB for local persistance, 
   remote service uses retrofit to connect with Remote services
2. di- used for dependency injection, using Hilt for the same, Api Module, Database Module and Repository module are provided using DI
3. ui- UI layer contains UI, and is divided into base and screen name folders
    
    base- contains base activity, I am making use of Genrics, Each Activity extending Base Activity should take a View Model and View Binding
    Also I am making use of view binding as I found it better to use than using findViewById to refer each of the view element
   
    home-Contains Home Activity for home screen, HomeViewModel
    news-detail-Contains News Detail Activity, used to display news details
    saved-news- Contains Saved News Activity, used to display saved news
   Also I am using a single view model currently as the app is not that complex, a single view model can work for now
   
I have built it so that It can scale, there may be few issues in terms of code quality or architecture level but that can be fixed

