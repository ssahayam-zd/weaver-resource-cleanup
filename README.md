# Weaver-resource-cleanup #

A minimal scala project to investigate Weaver Resource clean up.


Running the tests demonstrates that the clean up for the SharedResource does not complete.

Run tests with:

```
sbt test
```

Output:

```
weaver-resource-cleanup> test
=====> Acquiring shared resource
=====> Acquired shared resource: hello world!
[info] acme.weaverissue.SharingSuite
[info] + a stranger, from the outside ! ooooh 33ms
[info] acme.weaverissue.OtherSharingSuite
[info] + oops, forgot something here 1ms
======> About to release shared resource: hello world!
[info] Passed: Total 2, Failed 0, Errors 0, Passed 2
[success]
```

Expected:

```
weaver-resource-cleanup> test
=====> Acquiring shared resource
=====> Acquired shared resource: hello world!
[info] acme.weaverissue.SharingSuite
[info] + a stranger, from the outside ! ooooh 33ms
[info] acme.weaverissue.OtherSharingSuite
[info] + oops, forgot something here 1ms
======> About to release shared resource: hello world!
======> Released shared resource: hello world! <-- this is missing from the above output
[info] Passed: Total 2, Failed 0, Errors 0, Passed 2
[success]
```

