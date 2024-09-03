package com.example.demo.service;

import java.util.List;

public class Validator {

	public static final String ERR_TYPE_REQUIRED = "REQUIRED_ERROR";
	public static final String ERR_TYPE_POSITIVE_NUMBER = "POSITIVE_NUMBER_ERROR";
	public static final String ERR_TYPE_NEGATIVE_NUMBER = "NEGATIVE_NUMBER_ERROR";
	public static final String ERR_TYPE_ALREADY_REGISTED = "ALREADY_REGISTED_ERROR";
	public static final String ERR_TYPE_LENGTH = "LENGTH_ERROR";
	
	
	private static final String MESSAGE_REQUIRED_ERR = "@fieldNameは必須です。";
	private static final String MESSAGE_POSITIVE_NUMBER_ERR = "@fieldNameは正の整数です。";
	private static final String MESSAGE_NEGATIVE_NUMBER_ERR = "@fieldNameは負の整数です。";
	
	private static final String MESSAGE_ALREADY_REGISTED_ERR = "@fieldNameはすでに登録されています。";
	private static final String MESSAGE_LENGTH_ERR = "@fieldNameは@length文字固定です。";
	private static final String MESSAGE_UNEXPECTED_ERR = "定義外のエラーです。";
	
	/**
	 * 文字数エラーメッセージを生成する
	 * @param errorList エラーメッセージリスト
	 * @param fieldName エラー対象の項目
	 * @param length    基準文字数
	 * @param errType   エラータイプ
	 */
	public static void createErrorMessage(List<String> errorList, String fieldName, Integer length, String errType) {
		String message = "";
		switch (errType) {
		case ERR_TYPE_LENGTH:
			message = MESSAGE_LENGTH_ERR;
			break;
		default:
			message = MESSAGE_UNEXPECTED_ERR;
			break;
		}
		message = message.replace("@fieldName", fieldName);
		message = message.replace("@length", length.toString());
		errorList.add(message);
	}
	
	/**
	 * エラーメッセージを生成する
	 * @param errorList エラーメッセージリスト
	 * @param fieldName エラー対象の項目
	 * @param errType   エラータイプ
	 */
	public static void createErrorMessage(List<String> errorList, String fieldName, String errType) {
		String message = "";
		switch (errType) {
		case ERR_TYPE_REQUIRED:
			message = MESSAGE_REQUIRED_ERR;
			break;
		case ERR_TYPE_POSITIVE_NUMBER:
			message = MESSAGE_POSITIVE_NUMBER_ERR;
			break;
		case ERR_TYPE_NEGATIVE_NUMBER:
			message = MESSAGE_NEGATIVE_NUMBER_ERR;
			break;
		case ERR_TYPE_ALREADY_REGISTED:
			message = MESSAGE_ALREADY_REGISTED_ERR;
			break;
		default:
			message = MESSAGE_UNEXPECTED_ERR;
			break;
		}
		message = message.replace("@fieldName", fieldName);
		errorList.add(message);
	}

	/**
	 * 文字数チェック
	 * @param target         チェック対象文字列
	 * @param expectedLength 規定文字数
	 * @return 体調文字列の文字数が規定文字数に等しい場合はtrue、それ以外はfalse
	 */
	public static boolean hasLength(String target, int expectedLength) {
		// チェック対象文字列がnullの場合：空文字列「」に置き換える
		if (isNull(target)) {
			target = "";
		}
		int length = target.length();
		return (length == expectedLength);
	}

	/**
	 * 負数チェック
	 * @param target チェック対象整数
	 * @return 対象整数が負である場合はtrue、それ以外はfalse
	 */
	public static boolean isNegativeNumber(int target) {
		return isUnder(0, target);
	}

	/**
	 * 正数チェック
	 * @param target チェック対象整数
	 * @return 対象整数が正である場合はtrue、それ以外はfalse
	 */
	public static boolean isPositiveNumber(int target) {
		return isOver(0, target);
	}
	
	/**
	 * 数値範囲未満チェック
	 * @param limit  範囲下限値
	 * @param target チェック対象整数
	 * @return 対象整数が範囲下限値未満である場合はtrue、それ以外はfalse
	 */
	public static boolean isUnder(int limit, int target) {
		return (target < limit);
	}
	
	/**
	 * 数値範囲超チェック
	 * @param limit  範囲上限値
	 * @param target チェック対象整数
	 * @return 対象整数が範囲上限値を超えている場合はtrue、それ以外はfalse
	 */
	public static boolean isOver(int limit, int target) {
		return (target > limit);
	}
	
	/**
	 * オブジェクトがnullかどうかをチェックする
	 * @param target チェック対象オブジェクト
	 * @return 対象オブジェクトがnullの場合はtrue、それ以外はfalse
	 */
	public static boolean isNull(Object target) {
		return (target == null);
	}
	
	/**
	 * 必須入力チェック
	 * @param target 検査対象変数
	 * @return 対象変数がnullまたは空文字列でないまたは整数である場合はtrue、それ以外はfalse
	 *         整数0が代入されている場合はtrueを返す
	 */
	public static boolean isRequired(Object target) {
		if (isNull(target)) {
			// nullの場合：どのような参照型であってもfalse
			return false;
		} else if (target instanceof String) {
			// 文字列の場合：空文字列であればfalse
			if (((String) target).isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
