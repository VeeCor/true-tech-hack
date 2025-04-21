# EasyStock Backend

## 🧠 Описание проекта

**EasyStock** — это backend-часть проекта по управлению складом. Backend реализован на Java с использованием Spring WebFlux, обеспечивая реактивный подход к работе с API True Tabs.  
Сервис разработан для взаимодействия с внешней платформой **[TrueTabs](https://truetabs.ru/)** от МТС, через которую происходит управление данными.

📦 **Frontend** репозиторий:  
🔗 [EasyStock Frontend](https://github.com/KatyaVarentsova/EasyStock)

---

## 🔌 Взаимодействие с TrueTabs API

Приложение подключается к платформе TrueTabs по REST API, получая и обновляя информацию о товарах.  
Для аутентификации используется токен, который необходимо указать как переменную окружения.

---

## 🚀 Развёртывание проекта

1. Убедитесь, что у вас установлен **Java 17** и **Maven**.
2. Клонируйте репозиторий:

```bash
git clone https://github.com/VeeCor/true-tech-hack.git
```

3. Откройте проект в **IntelliJ IDEA**.
4. Добавьте **переменные окружения**:

   В настройках конфигурации запуска (`Run > Edit Configurations`) добавьте:

   ```
   TOKEN=ваш_токен
   ```

   Это необходимо для подключения к TrueTabs API.

5. Запустите проект командой:

```bash
mvn spring-boot:run
```

---

## ⚙️ Технологии

- Java 17
- Spring Boot (WebFlux)
- Maven
- TrueTabs API
- REST

---

## 🧾 Лицензия

Проект разработан в рамках TrueTechHack и распространяется свободно.
