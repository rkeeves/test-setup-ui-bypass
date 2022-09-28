package io.github.rkeeves.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class MyBooksTableSteps {

    private final By table = By.cssSelector("div.profile-wrapper div.ReactTable");

    private final By paginator = By.cssSelector("div.profile-wrapper div.ReactTable .pagination-bottom");

    @And("MyBooks table is set to show {int} rows")
    public void tableIsSetToShowRowsPerPage(int numberOfRows) {
        $(paginator).$("select[aria-label='rows per page']")
                .selectOptionByValue(Integer.toString(numberOfRows));
    }

    @And("MyBooks table is set to show page {int}")
    public void tableIsSetToShowPage(int pageNumber) {
        final var input = $(paginator).$("input[aria-label='jump to page']");
        input.sendKeys(Keys.chord(Keys.CONTROL, "A"), Keys.BACK_SPACE, Integer.toString(pageNumber), Keys.ENTER);
        input.shouldHave(value(Integer.toString(pageNumber)));
    }

    @Then("MyBooks table shows {int} books on the current page")
    public void tableShowsBooksOnCurrentPage(int shownBooks) {
        $(table).$$(".rt-tbody > .rt-tr-group img")
                .shouldHave(size(shownBooks));
    }

    @And("MyBooks table has {int} pages")
    public void tableHasPages(int pageCount) {
        $(table).$(".-totalPages")
                .shouldHave(text(Integer.toString(pageCount)));
    }
}
