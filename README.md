# Ultimate Mod (Fabric 1.21.11)

Предметы: Обсидиановый Слиток, Железная палочка, Ultimate Workbench, Обсидиановая Кирка.
Lang-файлов нет — названия задаются в `ModItems.java` (вверху, 4 строки NAME_*).

## Как собрать .jar (без ПК, через GitHub)
1. Создай репозиторий на github.com и загрузи в него ВСЕ файлы из этого архива
   (включая папку `.github/workflows/build.yml`).
2. Вкладка **Actions** → workflow **build** → дождись зелёной галочки (2–4 минуты).
3. Открой запуск → внизу **Artifacts** → скачай `ultimatemod-jar` → внутри `ultimatemod-1.0.0.jar`.

## Сборка на ПК
Gradle 9.2+ и Java 21:  `gradle build`  → `build/libs/ultimatemod-1.0.0.jar`

## Установка (MojoLauncher)
- Установи Fabric Loader для 1.21.11 (Java 21).
- В папку `mods` положи: `ultimatemod-1.0.0.jar` + **Fabric API** для 1.21.11.

## Рецепты
- Обсидиановый слиток — переплавка обсидиана в печи.
- Железная палочка — 3 железных слитка по диагонали (A3, B2, C1).
- Ultimate Workbench — железо по углам и бокам, алмазы A2 и C2, медный слиток в центре.
- Обсидиановая кирка — только в Ultimate Workbench: слитки A1–A3, палочки B2 и C2.
  Ultimate Workbench умеет и все обычные рецепты.
  Рецепты кирки зашиты в `UltimateRecipes.java`.
