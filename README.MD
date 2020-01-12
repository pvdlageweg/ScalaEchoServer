# Echo server implemented in Scala

This implements [RFC862](https://tools.ietf.org/html/rfc862) 'Echo Protocol'. The only deviation from the specification
is that this implementation listen on port 12345 and not on port 7 so the
program doesn't need root rights on *NIX machines.

#### Starting the application

On the command line simply run

```
sbt run
```