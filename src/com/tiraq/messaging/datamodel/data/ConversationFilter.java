/*
 * Copyright (C) 2025 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tiraq.messaging.datamodel.data;

import com.tiraq.messaging.datamodel.data.ConversationListItemData.ConversationListViewColumns;

/**
 * Category filters for the conversation list (All / Personal / OTP / Offers).
 *
 * <p>The categories are derived heuristically from the latest message snippet
 * (and the conversation name) using SQL {@code LIKE} matches, so no database
 * schema change is required. "Personal" is everything that is neither an OTP
 * nor an offer.</p>
 */
public enum ConversationFilter {
    ALL,
    PERSONAL,
    OTP,
    OFFERS;

    // Keywords that identify a one-time-password / verification message.
    private static final String[] OTP_KEYWORDS = {
        "otp",
        "one time password",
        "one-time password",
        "verification code",
        "authentication code",
        "security code",
        "login code",
        "valid for",
        "do not share",
        "use this code",
        "code is",
    };

    // Keywords that identify a promotional / transactional offer message.
    private static final String[] OFFER_KEYWORDS = {
        "offer",
        "discount",
        "coupon",
        "cashback",
        "cash back",
        "recharge",
        "promo",
        "limited",
        "reward",
        "gift",
        " off ",
        " sale ",
        " deal ",
        " free ",
        " win ",
        " won ",
        " flat ",
    };

    /**
     * @return a SQL fragment (leading " AND ") to append to the archived-status
     *         selection, or {@code null} for {@link #ALL}.
     */
    public String getSelection() {
        switch (this) {
            case OTP:
                return " AND " + buildLike(snippetColumn(), OTP_KEYWORDS);
            case OFFERS:
                return " AND " + buildLike(snippetColumn(), OFFER_KEYWORDS);
            case PERSONAL:
                return " AND (NOT " + buildLike(snippetColumn(), OTP_KEYWORDS)
                        + " AND NOT " + buildLike(snippetColumn(), OFFER_KEYWORDS) + ")";
            case ALL:
            default:
                return null;
        }
    }

    private static String snippetColumn() {
        return "COALESCE(" + ConversationListViewColumns.SNIPPET_TEXT + ", '')";
    }

    private static String buildLike(final String column, final String[] keywords) {
        final StringBuilder builder = new StringBuilder("(");
        for (int i = 0; i < keywords.length; i++) {
            if (i > 0) {
                builder.append(" OR ");
            }
            builder.append(column).append(" LIKE '%").append(keywords[i]).append("%'");
        }
        builder.append(")");
        return builder.toString();
    }
}
