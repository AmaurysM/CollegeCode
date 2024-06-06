Data Structures used

	For holding Users: TreeSet<User>

I used TreeSet because it is fast and efficient to look up information but it also blocks duplicates from occurring. This is great because each user has to be unique. A TreeSet does not keep order of insertion but there's no need to keep order of the users. When creating a TreeSet you don't need to specify the size, so it becomes a great choice when you need to hold an untold number of objects.

	For holding followers and friends: TreeSet<User>

I used TreeSet for both holding followers and friends for the same reason that I used it to hold users. All users are unique and there can be an untold number of users as the best choice.

	For holding liked and favorited Posts: LinkedList<Comment>

I was contemplating using a treeSet for holding likes and favorite posts because there can be an unknowable amount of posts made. TreeSet are also a lot faster at searching. But there is a problem, some people can post the same comment multiple times so if you use a TreeSet it will block some comments that are the same. I could have used a TreeMap instead to solve the problem but because I was tasked to make twitter or something like it I saw what twitter did. Twitter keeps track of all your liked and favorited posts in order. Both treeMaps and treeSets don't keep order. 

	For holding Posts: LinkedList<Comment>

To show the user feed I needed to keep the order of the comments posted. This is why I used a linked list. With it, I could keep the order and of the comments being inserted and I could keep an unknown number of them.

	For holding Comments under Post: linkedList<Comment>

I used a linked list to hold the comments under a post for the same reason that I used it on the posts themselves. I needed to keep track of the 

	Dictionary: HashSet<String>

A HashSet is the fastest data structure I know. We can quickly add, delete and look for elements as fast as possible, but there is a downside. You need to know the number of items that you will enter in the first place, when making the hashset. This is great for things like a dictionary because we already know how many words there are in a dictionary. We can just make a HashMap from it and we would rarely have to increase its size.
