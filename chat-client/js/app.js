const serverIP = '10.0.153.143';

window.app = {

	/**
	 * 后端服务发布的url地址
	 */
	serverUrl: 'http://' + serverIP + ':8080',

	/**
	 * netty服务后端发布的url地址
	 */
	nettyServerUrl: 'ws://' + serverIP + ':8088/ws',

	/**
	 * 图片服务器的url地址
	 */
	imgServerUrl: 'http://' + serverIP + ':8080/img/',

	/**
	 * 判断字符串是否为空
	 * @param {Object} str
	 */
	isNotNull: function(str) {
		if (str != null && str != "" && str != undefined) {
			return true;
		}
		return false;
	},

	isNull: function(str) {
		return !this.isNotNull(str);
	},

	/**
	 * 封装消息提示框，默认mui的不支持居中和自定义icon，所以使用h5+
	 * @param {Object} msg
	 * @param {Object} type
	 */
	showToast: function(msg, type) {
		plus.nativeUI.toast(msg, {
			icon: "image/" + type + ".png",
			verticalAlign: "center"
		})
	},

	/**
	 * 保存用户的全局对象
	 * @param {Object} user
	 */
	setUserGlobalInfo: function(user) {
		var userInfoStr = JSON.stringify(user);
		plus.storage.setItem("userInfo", userInfoStr);
	},

	/**
	 * 获取用户的全局对象
	 */
	getUserGlobalInfo: function() {
		var userInfoStr = plus.storage.getItem("userInfo");
		return JSON.parse(userInfoStr);
	},

	/**
	 * 登出后，移除用户全局对象
	 */
	userLogout: function() {
		plus.storage.removeItem("userInfo");
	},

	/**
	 * 保存用户的联系人列表
	 * @param {Object} contactList
	 */
	setContactList: function(contactList) {
		var contactListStr = JSON.stringify(contactList);
		plus.storage.setItem("contactList", contactListStr);
	},

	/**
	 * 获取本地缓存中的联系人列表
	 */
	getContactList: function() {
		var contactListStr = plus.storage.getItem("contactList");
		if (this.isNull(contactListStr)) {
			return [];
		}
		return JSON.parse(contactListStr);
	},

	/**
	 * 根据用户id，从本地的缓存（联系人列表）中获取朋友的信息
	 * @param {Object} friendUserId
	 */
	getFriendFromContactList: function(friendUserId) {
		var contactListStr = plus.storage.getItem("contactList");
		if (this.isNotNull(contactListStr)) {
			var contactList = JSON.parse(contactListStr);
			for (var i = 0; i < contactList.length; i++) {
				if (contactList[i].friendUserId == friendUserId) {
					return contactList[i];
				}
			}
		}
		return null;
	},
	
	/**
	* 单个聊天记录的对象
	* @param {Object} userId
	* @param {Object} friendUserId
	* @param {Object} msg
	* @param {Object} isSend
	*/
	ChatHistory: function(userId, friendUserId, msg, isSend) {
		this.userId = userId;
		this.friendUserId = friendUserId;
		this.msg = msg;
		this.isSend = isSend;
	},

	/**
	 * 保存用户的聊天记录
	 * @param {Object} userId
	 * @param {Object} friendUserId
	 * @param {Object} msg
	 * @param {Object} isSend 判断消息是 user 发送的还是接收的
	 */
	saveUserChatHistory: function(userId, friendUserId, msg, isSend) {
		var chatKey = `chat-${userId}-${friendUserId}`;
		// 从本地缓存获取聊天记录是否存在
		var chatHistoryListStr = plus.storage.getItem(chatKey);
		var chatHistoryList = [];
		if (this.isNotNull(chatHistoryListStr)) {
			chatHistoryList = JSON.parse(chatHistoryListStr);
		}
		// 构建聊天记录对象
		var chatHistory = new this.ChatHistory(userId, friendUserId, msg, isSend);
		chatHistoryList.push(chatHistory);
		plus.storage.setItem(chatKey, JSON.stringify(chatHistoryList));
	},

	/**
	 * 获取聊天记录
	 * @param {Object} userId
	 * @param {Object} friendUserId
	 */
	getUserChatHistory: function(userId, friendUserId) {
		var chatKey = `chat-${userId}-${friendUserId}`;
		var chatHistoryListStr = plus.storage.getItem(chatKey);
		var chatHistoryList = [];
		if (this.isNotNull(chatHistoryListStr)) {
			chatHistoryList = JSON.parse(chatHistoryListStr);
		}
		return chatHistoryList;
	},

	// 删除聊天记录
	deleteUserChatHistory: function(userId, friendUserId) {
		var chatKey = `chat-${userId}-${friendUserId}`;
		plus.storage.removeItem(chatKey);
	},
	
	/**
	* 快照对象
	* @param {Object} userId
	* @param {Object} friendUserId
	* @param {Object} msg
	* @param {Object} isRead	用于判断消息是否已读还是未读
	*/
	ChatSnapshot: function(userId, friendUserId, msg, isRead) {
		this.userId = userId;
		this.friendUserId = friendUserId;
		this.msg = msg;
		this.isRead = isRead;
	},

	/**
	 * 聊天记录的快照，仅仅保存每次和朋友聊天的最后一条消息
	 * @param {Object} userId
	 * @param {Object} friendUserId
	 * @param {Object} msg
	 * @param {Object} isRead
	 */
	saveUserChatSnapshot: function(userId, friendUserId, msg, isRead) {
		var chatKey = `chat-snapshot${userId}`;
		var chatSnapshotList = [];
		var chatSnapshotListStr = plus.storage.getItem(chatKey);
		if (this.isNotNull(chatSnapshotListStr)) {
			// 循环快照list，并且判断每个元素是否包含（匹配）friendUserId，如果匹配，则删除
			chatSnapshotList = JSON.parse(chatSnapshotListStr)
				.filter(chatSnapshot => chatSnapshot.friendUserId != friendUserId);
		}
		// 构建聊天快照对象
		var chatSnapshot = new this.ChatSnapshot(userId, friendUserId, msg, isRead);
		// 向 chatSnapshotList 最前端追加快照对象
		chatSnapshotList.unshift(chatSnapshot);
		plus.storage.setItem(chatKey, JSON.stringify(chatSnapshotList));
	},

	/**
	 * 获取用户快照记录列表
	 */
	getUserChatSnapshot: function(userId) {
		var chatKey = `chat-snapshot${userId}`;
		var chatSnapshotList = [];
		var chatSnapshotListStr = plus.storage.getItem(chatKey);
		if (this.isNotNull(chatSnapshotListStr)) {
			chatSnapshotList = JSON.parse(chatSnapshotListStr);
		}
		return chatSnapshotList;
	},

	/**
	 * 删除本地的聊天快照记录
	 * @param {Object} userId
	 * @param {Object} friendUserId
	 */
	deleteUserChatSnapshot: function(userId, friendUserId) {
		var chatKey = `chat-snapshot${userId}`;
		var chatSnapshotList = [];
		var chatSnapshotListStr = plus.storage.getItem(chatKey);
		if (this.isNotNull(chatSnapshotListStr)) {
			// 循环快照list，并且判断每个元素是否包含（匹配）friendUserId，如果匹配，则删除
			chatSnapshotList = JSON.parse(chatSnapshotListStr)
				.filter(chatSnapshot => chatSnapshot.friendUserId != friendUserId);
			plus.storage.setItem(chatKey, JSON.stringify(chatSnapshotList));
		}
	},

	/**
	 * 标记未读消息为已读状态
	 * @param {Object} userId
	 * @param {Object} friendUserId
	 */
	readUserChatSnapshot: function(userId, friendUserId) {
		var chatKey = `chat-snapshot${userId}`;
		var chatSnapshotListStr = plus.storage.getItem(chatKey);
		var chatSnapshotList = [];
		if (this.isNotNull(chatSnapshotListStr)) {
			chatSnapshotList = JSON.parse(chatSnapshotListStr);
			chatSnapshotList.forEach(chatSnapshot => {
				if(chatSnapshot.friendUserId == friendUserId) {
					chatSnapshot.isRead = true;
				}
			});
			// console.log(`readUserChatSnapshot: ${JSON.stringify(chatSnapshotList)}`)
			plus.storage.setItem(chatKey, JSON.stringify(chatSnapshotList));
		}
	},

	/**
	 * 和后端的枚举对应
	 */
	CONNECT: 1, // 第一次(或重连)初始化连接
	CHAT: 2, // 聊天消息
	SIGNED: 3, // 消息签收
	KEEP_ALIVE: 4, // 客户端保持心跳
	PULL_FRIEND: 5, // 重新拉取好友

	/**
	 * 和后端的 ChatMsg 聊天模型对象保持一致
	 * @param {Object} sendUserId
	 * @param {Object} acceptUserId
	 * @param {Object} msg
	 * @param {Object} msgId
	 */
	ChatMsg: function(sendUserId, acceptUserId, msg, msgId) {
		this.sendUserId = sendUserId;
		this.acceptUserId = acceptUserId;
		this.msg = msg;
		this.msgId = msgId;
	},

	/**
	 * 构建消息 Content 模型对象
	 * @param {Object} action
	 * @param {Object} chatMsg
	 * @param {Object} extend
	 */
	Content: function(action, chatMsg, extend) {
		this.action = action;
		this.chatMsg = chatMsg;
		this.extend = extend;
	},

	/**
	 * 读取未发送成功的消息
	 */
	readUnsentMessageList: function() {
		var unsentMessageListStr = plus.storage.getItem("unsentMessageList");
		plus.storage.removeItem("unsentMessageList");
		var unsentMessageList = [];
		if (unsentMessageListStr != null) {
			unsentMessageList = JSON.parse(unsentMessageListStr);
		}
		console.log(`readUnsentMessageList: ${unsentMessageList}`);
		return unsentMessageList;
	},

	/**
	 * 保存未发送成功的消息
	 */
	saveUnsentMessageList: function(msg) {
		var unsentMessageListStr = plus.storage.getItem("unsentMessageList");
		var unsentMessageList = [];
		if (unsentMessageListStr != null) {
			unsentMessageList = JSON.parse(unsentMessageListStr);
		}
		unsentMessageList.push(msg);
		plus.storage.setItem("unsentMessageList", JSON.stringify(unsentMessageList));
		console.log(`saveUnsentMessageList: ${unsentMessageList}`);
	}
}
