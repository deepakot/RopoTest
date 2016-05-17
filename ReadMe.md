RopoTest

Problem Statement: We are required to create an app which contains stories. First view will be a listview which contains story cards and the second page is the detail page.

Challenge: If user clicks on follow on one story from a celebrity then it should get reflected to all the views of that particular user without changing all the views status and without using notifydatasetchanged().

Solution: Created MainActivty which contains a recyclerview. Recyclerview is filled with a custom StoryListAdapter. For the detail page I used a DialogFragment.

Created two objects which contains storyList and userList. And passed these to the adapter.

Created an interface listener and implemented that in MainActivity. Listener has two functions, one to open the DetailDialogFragment and other to change the follow status of views.

As the views are created dynamically so we cannot just set the change in every view. Instead we will be changing the follow status in the storyList itself. And when the new views are created they'll be created from the updated storyList.

Now what about the visible views which have already been created?

In the listener in MainActivity we get the FirstVisibleItem using the findFirstVisibleItemPosition() and LastVisibleItem using the findLastVisibleItemPosition(). And we change the image(which represents the status) from FirstVisibleItem to LastVisibleItem.