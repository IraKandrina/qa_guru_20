package com.demoqa.tests;

import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteBookModel;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.demoqa.tests.TestData.credentials;

public class ProfileBooksListTests extends TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    ProfilePage profilePage = new ProfilePage();
    BooksApi booksApi = new BooksApi();

    @Test
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);

        profilePage.openProfileWithCookies(loginResponse)
                .checkBookIsVisible("Git Pocket Guide");
    }

    @Test
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);
        DeleteBookModel deleteBookModel = new DeleteBookModel("9781449325862", loginResponse.getUserId());
        booksApi.deleteBook(loginResponse,deleteBookModel);

        profilePage.openProfileWithCookies(loginResponse)
                .checkBookListIsEmpty("No rows found");
    }
}
