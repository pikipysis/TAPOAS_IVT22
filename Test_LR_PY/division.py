class User:
    def __init__(self, username, role):
        if not username:
            raise ValueError("Имя пользователя не может быть пустым")
        if role not in ['admin', 'user', 'guest']:
            raise ValueError("Роль пользователя может быть только 'admin', 'user', или 'guest'")
        
        self.username = username
        self.role = role

    def __repr__(self):
        return f"User(username={self.username}, role={self.role})"


class UserManager:
    def __init__(self):
        self.users = []

    def add_user(self, username, role):
        user = User(username, role)
        self.users.append(user)
        return user

    def remove_user(self, username):
        user_to_remove = self.get_user_by_username(username)
        if user_to_remove:
            self.users.remove(user_to_remove)
            return user_to_remove
        raise ValueError("Пользователь не найден")

    def get_user_by_username(self, username):
        for user in self.users:
            if user.username == username:
                return user
        return None

    def get_users_by_role(self, role):
        return [user for user in self.users if user.role == role]

    def update_user_role(self, username, new_role):
        if new_role not in ['admin', 'user', 'guest']:
            raise ValueError("Роль пользователя может быть только 'admin', 'user', или 'guest'")
        user = self.get_user_by_username(username)
        if user:
            user.role = new_role
            return user
        raise ValueError("Пользователь не найден")
