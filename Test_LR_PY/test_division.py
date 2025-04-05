import unittest
from division import *  

class TestUserManager(unittest.TestCase):
    
    def setUp(self):
        self.manager = UserManager()
        self.manager.add_user("admin1", "admin")
        self.manager.add_user("user1", "user")
        self.manager.add_user("guest1", "guest")
        self.test_log = ""  

    def log_message(self, message):
        self.test_log += f"{message} | "
    def setUpTestLog(self):
        self.test_log = f"Запуск теста '{self._testMethodName}'. "
        self.log_message(f"Начало выполнения теста: {self._testMethodName}")

    def test_add_user(self):
        self.setUpTestLog()        
        user = self.manager.add_user("new_user", "user")
        self.log_message(f"Добавлен пользователь: {user.username}, роль: {user.role}.")

        self.assertEqual(user.username, "new_user", f"{self.test_log}Ошибка: имя пользователя не совпадает.")
        self.assertEqual(user.role, "user", f"{self.test_log}Ошибка: роль пользователя не совпадает.")
        self.assertEqual(len(self.manager.users), 4, f"{self.test_log}Ошибка: количество пользователей не совпадает.")
        
        print(f"{self.test_log}Тест 'test_add_user' завершен.")
    
    def test_remove_user(self):
        self.setUpTestLog()
        
        user = self.manager.remove_user("user1")
        self.log_message(f"Удалён пользователь: {user.username}.")
        
        self.assertEqual(user.username, "user1", f"{self.test_log}Ошибка: имя удалённого пользователя не совпадает.")
        self.assertEqual(len(self.manager.users), 1, f"{self.test_log}Ошибка: количество пользователей не совпадает.")
        
        print(f"{self.test_log}Тест 'test_remove_user' завершен.")
    
    def test_remove_user_not_found(self):
        self.setUpTestLog()
        with self.assertRaises(ValueError):
            self.manager.remove_user("nonexistent_user")
        
        self.log_message("Попытка удалить несуществующего пользователя.")
        
        print(f"{self.test_log}Тест 'test_remove_user_not_found' завершен.")

    def test_get_user_by_username(self):
        self.setUpTestLog()
        
        user = self.manager.get_user_by_username("admin1")
        self.log_message(f"Найден пользователь: {user.username}, роль: {user.role}.")
        self.assertEqual(user.username, "admin1", f"{self.test_log}Ошибка: имя пользователя не совпадает.")
        self.assertEqual(user.role, "admin", f"{self.test_log}Ошибка: роль пользователя не совпадает.")
        
        print(f"{self.test_log}Тест 'test_get_user_by_username' завершен.")

    def test_update_user_role(self):
        self.setUpTestLog()
        
        user = self.manager.update_user_role("user1", "admin")
        self.log_message(f"Обновлена роль пользователя {user.username} на {user.role}.")
        
        self.assertEqual(user.role, "admin", f"{self.test_log}Ошибка: роль не обновлена.")
        
        print(f"{self.test_log}Тест 'test_update_user_role' завершен.")

    def test_update_user_role_invalid(self):
        self.setUpTestLog()
        
        with self.assertRaises(ValueError):
            self.manager.update_user_role("admin1", "invalid_role")
        
        self.log_message("Попытка обновления роли на недопустимое значение.")
        
        print(f"{self.test_log}Тест 'test_update_user_role_invalid' завершен.")

    def test_create_user_with_invalid_role(self):
        self.setUpTestLog()
        
        with self.assertRaises(ValueError):
            self.manager.add_user("new_user", "invalid_role")
        
        self.log_message("Попытка создания пользователя с недопустимой ролью.")
        
        print(f"{self.test_log}Тест 'test_create_user_with_invalid_role' завершен.")

    def test_create_user_with_empty_username(self):
        self.setUpTestLog()
        
        with self.assertRaises(ValueError):
            self.manager.add_user("", "user")
        
        self.log_message("Попытка создания пользователя с пустым именем.")
        
        print(f"{self.test_log}Тест 'test_create_user_with_empty_username' завершен.")

    def test_get_users_by_role(self):
        self.setUpTestLog()
        
        users = self.manager.get_users_by_role("admin")
        self.log_message(f"Пользователи с ролью 'admin': {', '.join([user.username for user in users])}.")
        
        self.assertEqual(len(users), 1, f"{self.test_log}Ошибка: количество пользователей с ролью 'admin' не совпадает.")
        
        print(f"{self.test_log}Тест 'test_get_users_by_role' завершен.")

    def test_update_user_role_not_found(self):
        self.setUpTestLog()
        with self.assertRaises(ValueError):
            self.manager.update_user_role("nonexistent_user", "user")
        
        self.log_message("Попытка обновления роли для несуществующего пользователя.")
        
        print(f"{self.test_log}Тест 'test_update_user_role_not_found' завершен.")

if __name__ == '__main__':
    unittest.main()
