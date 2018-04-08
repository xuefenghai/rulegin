/**
 * Copyright © 2016-2017 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.neucloud.server.dao.user;


import cn.neucloud.server.common.data.User;
import cn.neucloud.server.common.data.id.UserId;
import cn.neucloud.server.common.data.page.TextPageData;
import cn.neucloud.server.common.data.page.TextPageLink;
import com.google.common.util.concurrent.ListenableFuture;

public interface UserService {
	
	User findUserById(UserId userId);

	ListenableFuture<User> findUserByIdAsync(UserId userId);

	User findUserByEmail(String email);

	User saveUser(User user);

	UserCredentials findUserCredentialsByUserId(UserId userId);
	
	UserCredentials findUserCredentialsByActivateToken(String activateToken);

	UserCredentials findUserCredentialsByResetToken(String resetToken);

	UserCredentials saveUserCredentials(UserCredentials userCredentials);
	
	UserCredentials activateUserCredentials(String activateToken, String password);
	
	UserCredentials requestPasswordReset(String email);

	void deleteUser(UserId userId);
	
	TextPageData<User> findUserAdmins(TextPageLink pageLink);
	TextPageData<User> findCustomerUsers(UserId customerId, TextPageLink pageLink);

}
