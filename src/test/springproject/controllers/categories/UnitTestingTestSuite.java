package springproject.controllers.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import springproject.controllers.BookControllerUnitTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(UnitTesting.class)
@Suite.SuiteClasses(BookControllerUnitTest.class)
public class UnitTestingTestSuite {
}
