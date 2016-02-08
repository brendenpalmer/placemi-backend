# Backend for placemi.com

* The backend that powers [placemi.com](http://placemi.com)
* Built using Java and Spring.

## Installation

Ensure that Java 8+ and maven are both installed.

Finally, install all development dependencies:

```
cd CLONED_REPO_DIR
mvn clean install
```

Then, ensure you create two properties files under /src/main/resources:

1. application.properties
 - base.image.path=/PATH/WHERE/IMAGES/ARE/STORED
2. database.properties
 - driver=com.mysql.jdbc.Driver
 - url=URL_TO_DATABASE/SCHEMA
 - username=username
 - password=password

## Usage

If you're interested in contributing, see the [Contributing](https://github.com/brendenpalmer/placemi-backend#contributing) section below.

## Documentation

The built documentation will always be located here: [Documentation](https://github.com/brendenpalmer/placemi-backend/tree/master/docs).

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request.

## History

See the [CHANGELOG](https://github.com/brendenpalmer/placemi-backend/blob/master/CHANGELOG.md).

## License

Licensed under MIT. See the full license here: [license](https://github.com/brendenpalmer/placemi-backend/blob/master/LICENSE).
