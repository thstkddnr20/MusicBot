/*
 * Copyright 2016 John Grosh <john.a.grosh@gmail.com>.
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
package com.jagrosh.jmusicbot.commands.music;

import com.jagrosh.jmusicbot.Bot;

/**
 *
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class SCSearchCmd extends SearchCmd 
{
    public SCSearchCmd(Bot bot)
    {
        super(bot);
        this.searchPrefix = "사운드클라우드검색:";
        this.name = "사운드클라우드검색";
        this.help = "주어진 검색어로 사운드 클라우드에서 검색합니다";
        this.aliases = bot.getConfig().getAliases(this.name);
    }
}
