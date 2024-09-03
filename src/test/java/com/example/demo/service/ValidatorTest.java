package com.example.demo.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ValidatorTest {

	@Nested
	@DisplayName("Validator#createErrorMessage(List, String, int, String)メソッドのテストクラス")
	class CreateErrorMessageTestForLength {
		
		/** テスト補助変数 */
		List<String> errorList;
		
		@BeforeEach
		void setUp() {
			errorList = new ArrayList<String>();
		}
		
		@ParameterizedTest
		@MethodSource("paramsProvider")
		@DisplayName("Test：項目名とエラータイプを指定して生成されるメッセージのテスト")
		void test(String fieldName, int length, String errType, String expected) {
			// execute
			Validator.createErrorMessage(errorList, fieldName, length, errType);
			// verify
			String actual = errorList.get(0);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider() {
			// setup
			List<String>  fieldNameList = new ArrayList<String>();
			List<Integer> lengthList = new ArrayList<Integer>();
			List<String>  errTypeList = new ArrayList<String>();
			List<String>  expectedList = new ArrayList<String>();
			
			// Test01: 項目名「IMEI」・文字数「15」・エラータイプ「ERR_TYPE_LENGTH」⇒「IMEIは15文字固定です。」
			fieldNameList.add("IMEI");
			lengthList.add(15);
			errTypeList.add(Validator.ERR_TYPE_LENGTH);
			expectedList.add("IMEIは15文字固定です。");
			// Test02: 項目名「郵便番号」・文字数「8」・エラータイプ「ERR_TYPE_LENGTH」⇒「郵便番号は8文字固定です。」
			fieldNameList.add("郵便番号");
			lengthList.add(8);
			errTypeList.add(Validator.ERR_TYPE_LENGTH);
			expectedList.add("郵便番号は8文字固定です。");
			// Test03: 項目名「利用者名」・文字数「-2」・エラータイプ「ERR_UNEXPEcTED」⇒「定義外のエラーです。」
			fieldNameList.add("利用者名");
			lengthList.add(-2);
			errTypeList.add("");
			expectedList.add("定義外のエラーです。");
			
			// テストパラメータを返却
			return Stream.of(
						  Arguments.of(fieldNameList.get(0), lengthList.get(0), errTypeList.get(0), expectedList.get(0))
						, Arguments.of(fieldNameList.get(1), lengthList.get(1), errTypeList.get(1), expectedList.get(1))
						, Arguments.of(fieldNameList.get(2), lengthList.get(2), errTypeList.get(2), expectedList.get(2))
					);
		}
		
	}
	
	@Nested
	@DisplayName("Validator#createErrorMesasge(List, String, String)メソッドのテストクラス")
	class CreateErrorMessageTest {
		
		/** テスト補助変数 */
		List<String> errorList;
		
		@BeforeEach
		void setUp() {
			errorList = new ArrayList<String>();
		}
		
		@ParameterizedTest
		@MethodSource("paramsProvider")
		@DisplayName("Test：項目名とエラータイプを指定して生成されるメッセージのテスト")
		void test01(String fieldName, String errType, String expected) {
			// execute
			Validator.createErrorMessage(errorList, fieldName, errType);
			// verify
			String actual = errorList.get(0);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider() {
			/// setup
			List<String> fieldNameList = new ArrayList<>();
			List<String> errTypeList = new ArrayList<>();
			List<String> expectedList = new ArrayList<String>();
			
			// Test01: 項目名「利用者」・エラータイプ「ERR_TYPE_REQUIRED（必須入力エラー）」⇒「利用者は必須です」
			fieldNameList.add("利用者");
			errTypeList.add(Validator.ERR_TYPE_REQUIRED);
			expectedList.add("利用者は必須です。");
			// Test02: 項目名「管理番号」・エラータイプ「ERR_TYPE_POSITIVE_NUMBER（正数値エラー）」⇒「管理番号は正の整数です。」
			fieldNameList.add("管理番号");
			errTypeList.add(Validator.ERR_TYPE_POSITIVE_NUMBER);
			expectedList.add("管理番号は正の整数です。");
			// Test03: 項目名「氷点下」・エラータイプ「ERR_TYPE_NEGATIVE_NUMBER（負数値エラー）」⇒「氷点下は負の整数です。」
			fieldNameList.add("氷点下");
			errTypeList.add(Validator.ERR_TYPE_NEGATIVE_NUMBER);
			expectedList.add("氷点下は負の整数です。");
			// Test04: 項目名「デッカード大差」・エラータイプ「ERR_TYPE_ALREADY_REGISTED（登録済エラー）」⇒「デッカード大差はすでに登録されています。」
			fieldNameList.add("デッカード大差");
			errTypeList.add(Validator.ERR_TYPE_ALREADY_REGISTED);
			expectedList.add("デッカード大差はすでに登録されています。");
			// Test05: 項目名「住所」・エラータイプ「ERR_TYPE_UNEXPECTED（定義外エラー）」⇒「定義外のエラーです。」
			fieldNameList.add("住所");
			errTypeList.add("");
			expectedList.add("定義外のエラーです。");
			
			// パラメータを返却
			return Stream.of(
					    Arguments.of(fieldNameList.get(0), errTypeList.get(0), expectedList.get(0))
					  , Arguments.of(fieldNameList.get(1), errTypeList.get(1), expectedList.get(1))
					  , Arguments.of(fieldNameList.get(2), errTypeList.get(2), expectedList.get(2))
					  , Arguments.of(fieldNameList.get(3), errTypeList.get(3), expectedList.get(3))
					  , Arguments.of(fieldNameList.get(4), errTypeList.get(4), expectedList.get(4))
					);
		}
		
	}
	
	@Nested
	@DisplayName("Validator#createRequiredError(List, String)メソッドのテストクラス")
	class CreateRequiredErrorTest {
		@Test
		@DisplayName("Test01：利用者の項目の必須入力エラーメッセージは「利用者名は必須です。」である")
		void test01() {
			// setup
			List<String> errorList = new ArrayList<>();
			String target = "利用者名";
			String expected = "利用者名は必須です。";
			// execute
			Validator.createErrorMessage(errorList, target, Validator.ERR_TYPE_REQUIRED);
			String actual = errorList.get(0);
			// verify
			assertThat(actual, is(expected));
		}
	}
	
	@Nested
	@DisplayName("Validator#hasLength(Integer, String)メソッドのテストクラス")
	class HasLengthTest {
		
		@ParameterizedTest
		@MethodSource("paramsProvider")
		@DisplayName("Test：範囲の下限値未満であるかどうかを検査するテスト")
		void test(String target, int length, boolean expected) {
			// executte & verify
			boolean actual = Validator.hasLength(target, length);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider() {
			// setup
			List<String> targetList = new ArrayList<String>();
			List<Integer> lengthList = new ArrayList<Integer>();
			List<Boolean> expectedList = new ArrayList<Boolean>();
			
			// Test01: 「イザナギノミコト」は8文字である
			targetList.add("イザナギノミコト");
			lengthList.add(8);
			expectedList.add(true);
			// Test02: 「マルタイ棒ラーメン」は8文字ではない
			targetList.add("マルタイ棒ラーメン");
			lengthList.add(8);
			expectedList.add(false);
			// Test03: nullの文字数は0である
			targetList.add(null);
			lengthList.add(0);
			expectedList.add(true);
			// Test04: 空文字列は0文字である
			targetList.add("");
			lengthList.add(0);
			expectedList.add(true);
			
			// テストパラメータを返却
			return Stream.of(
						  Arguments.of(targetList.get(0), lengthList.get(0), expectedList.get(0))
						, Arguments.of(targetList.get(1), lengthList.get(1), expectedList.get(1))
						, Arguments.of(targetList.get(2), lengthList.get(2), expectedList.get(2))
						, Arguments.of(targetList.get(3), lengthList.get(3), expectedList.get(3))
					);
		}
		
	}
	
	@Nested
	@DisplayName("Validator#isUnder(int, int)メソッドのテストクラス")
	class IsUnderTest {
		
		@ParameterizedTest
		@MethodSource("paramsProvider")
		@DisplayName("Test：範囲の下限値未満であるかどうかを検査するテスト")
		void test(int limit, int target, boolean expected) {
			// execute & verify
			boolean actual = Validator.isUnder(limit, target);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider() {
			// setup
			List<Integer> limitList = new ArrayList<Integer>();
			List<Integer> targetList = new ArrayList<Integer>();
			List<Boolean> expectedList = new ArrayList<Boolean>();
			
			// Test01: 4は5未満である
			limitList.add(5);
			targetList.add(4);
			expectedList.add(true);
			// Test02: 5は5未満ではない
			limitList.add(5);
			targetList.add(5);
			expectedList.add(false);
			// Test02: 6は5未満ではない
			limitList.add(5);
			targetList.add(6);
			expectedList.add(false);
			
			// テストパラメータを返却
			return Stream.of(
						  Arguments.of(limitList.get(0), targetList.get(0), expectedList.get(0))
						, Arguments.of(limitList.get(1), targetList.get(1), expectedList.get(1))
						, Arguments.of(limitList.get(2), targetList.get(2), expectedList.get(2))
					);
		}
		
	}
	
	@Nested
	@DisplayName("Validator#isOver(int, int)メソッドのテストクラス")
	class IsOverThanTest {
		@ParameterizedTest
		@MethodSource("paramsProvider")
		@DisplayName("Test：範囲の上限値を超えるかどうかを検査するテスト")
		void test(int limit, int target, boolean expected) {
			// execute & verify
			boolean actual = Validator.isOver(limit, target);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider() {
			// setup
			List<Integer> limitList = new ArrayList<Integer>();
			List<Integer> targetList = new ArrayList<Integer>();
			List<Boolean> expectedList = new ArrayList<Boolean>();
			
			// Test01: 6は5を超える
			limitList.add(5);
			targetList.add(6);
			expectedList.add(true);
			// Test02: 5は5を超えない
			limitList.add(5);
			targetList.add(5);
			expectedList.add(false);
			// Test03: 4は5を超えない
			limitList.add(5);
			targetList.add(４);
			expectedList.add(false);
			
			// テストパラメータを返却
			return Stream.of(
						  Arguments.of(limitList.get(0), targetList.get(0), expectedList.get(0))
						, Arguments.of(limitList.get(1), targetList.get(1), expectedList.get(1))
						, Arguments.of(limitList.get(2), targetList.get(2), expectedList.get(2))
					);
		}
		
	}
	
	
	@Nested
	@DisplayName("Validator#isRequired(Object)メソッドのテストクラス")
	class IsRequiredTest {
		
		@ParameterizedTest
		@MethodSource("paramsProvider20")
		@DisplayName("Test20：整数に対するテスト")
		void test20(Integer target, Boolean expected) {
			// execute & verify
			Boolean actual = Validator.isRequired(target);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider20() {
			// setup
			List<Integer> targetList = new ArrayList<Integer>();
			List<Boolean> expectedList = new ArrayList<Boolean>();
			
			// Test21：整数2024が代入されている項目はtrueである
			targetList.add(2024);
			expectedList.add(true);
			// Test22: nullが代入されている項目はfalseである
			targetList.add(null);
			expectedList.add(false);
			
			// テストパラメータを返却
			return Stream.of(
						  Arguments.of(targetList.get(0), expectedList.get(0))
						, Arguments.of(targetList.get(1), expectedList.get(1))
					);
		}
		
		@ParameterizedTest
		@MethodSource("paramsProvider10")
		@DisplayName("Test10：文字列に対するテスト")
		void test10(String target, boolean expected) {
			// execute & verify
			boolean actual = Validator.isRequired(target);
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> paramsProvider10() {
			// setup
			List<String> targetList = new ArrayList<String>();
			List<Boolean> expectedList = new ArrayList<Boolean>();
			
			// Test11: 文字列「hoGe」が代入されている項目はtrueである
			targetList.add("hoGe");
			expectedList.add(true);
			// Test12: 空文字列が代入されている項目はfalseである
			targetList.add("");
			expectedList.add(false);
			// Test13: nullが代入されている項目はfalseである
			targetList.add(null);
			expectedList.add(false);
			
			// テストパラメータを返却
			return Stream.of(
						  Arguments.of(targetList.get(0), expectedList.get(0))
						, Arguments.of(targetList.get(1), expectedList.get(1))
						, Arguments.of(targetList.get(2), expectedList.get(2))
					);
		}
		
	}
}
