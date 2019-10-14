# docker-scheduler

Docker-based jobs scheduler on AWS Environment written in Clojure, for learning purposes. Uses [Compojure][compojure] to expose endpoints and [Amazonica][amazonica] to interact with AWS. 

[compojure]: https://github.com/weavejester/compojure
[amazonica]: https://github.com/mcohen01/amazonica
## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Tests

To run the tests:
    
    lein test

## License

Copyright © 2019 Victor F
