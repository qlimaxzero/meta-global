package com.shitouren.core.controller.v2;


import com.shitouren.core.aop.Access;
import com.shitouren.core.aop.LimitType;
import com.shitouren.core.aop.RateLimiter;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.model.vo.UserVO;
import com.shitouren.core.model.vo.GuildVO;
import com.shitouren.core.service.GuildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "新版 - 公会")
@RequestMapping("/v2/guild")
@RequiredArgsConstructor
@Validated
public class GuildController {

    private final GuildService guildService;

    @PostMapping("/join")
    @Access
    @ApiOperation("加入公会")
    public void join(@NotBlank(message = "Guild invitation code cannot be empty") @RequestParam String code) {
        guildService.join(WebContextHolder.get(), code.trim());
    }

    @PostMapping("/create")
    @ApiOperation("创建公会")
    @RateLimiter(time = 5, count = 1, limitType = LimitType.IP)
    public void create(@NotBlank(message = "Guild name cannot be empty") @RequestParam String name) {
        guildService.create(WebContextHolder.get(), name.trim());
    }

    @GetMapping("/members")
    @ApiOperation("公会成员信息")
    public List<UserVO> members(@RequestParam Integer guildId) {
        return guildService.members(guildId);
    }

    @GetMapping("/rankings")
    @ApiOperation("排行榜列表")
    public List<GuildVO> rankings() {
        return guildService.rankingList();
    }

}
