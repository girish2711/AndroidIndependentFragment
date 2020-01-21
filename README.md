# Android Independent Fragment
Repository will demonstrate how one can load a webview in background and then use it immediately when required to show in an activity
Fragment can be initialized at any point of an app life cycle and then one can call loadWebview with current main activity context
Later any point of time a activity can reuse this to show. 

Activity implements an interface to be able to communicate with fragment it is planning to attach. 
