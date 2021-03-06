[![License](https://img.shields.io/github/license/Blackoutburst/BossBar-API.svg)](LICENSE)
[![Release](https://img.shields.io/github/release/Blackoutburst/BossBar-API.svg)](https://github.com/Blackoutburst/BossBar-API/releases)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/080241be08834e358369f7d2258a22b5)](https://www.codacy.com/gh/Blackoutburst/BossBar-API/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Blackoutburst/BossBar-API&amp;utm_campaign=Badge_Grade)

# BossBar-API
A simple bossbar API using reflection

## Usage
Inside your `plugin.yml` put
```yaml
depend: [BossBarAPI]
```

Create a bossbar
```java
NMSBossBar bossBar = new NMSBossBar(player, "§aHello §6World!", 75);
```
![image](https://user-images.githubusercontent.com/30992311/155023030-e2ee8408-2421-4b30-8bce-c8e57913d84a.png)

Edit a bossbar with
```java
bossBar.setHealth(player, 100);
bossBar.setText(player, "§cTest");
```
![image](https://user-images.githubusercontent.com/30992311/155735498-799714bb-aab7-45c4-ac97-65545a9a8086.png)
