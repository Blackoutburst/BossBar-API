[![License](https://img.shields.io/github/license/Blackoutburst/QuakeCraft.svg)](LICENSE)
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

Preview\
![image](https://user-images.githubusercontent.com/30992311/155023030-e2ee8408-2421-4b30-8bce-c8e57913d84a.png)
