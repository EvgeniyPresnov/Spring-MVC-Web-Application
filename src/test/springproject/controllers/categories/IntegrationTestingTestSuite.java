package springproject.controllers.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import springproject.controllers.BookControllerIntegrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(IntegrationTesting.class)
@Suite.SuiteClasses(BookControllerIntegrationTest.class)
public class IntegrationTestingTestSuite {
}
