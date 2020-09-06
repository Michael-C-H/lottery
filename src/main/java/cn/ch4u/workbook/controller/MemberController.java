package cn.ch4u.workbook.controller;

import cn.ch4u.workbook.entity.Member;
import cn.ch4u.workbook.service.IMemberService;
import cn.ch4u.workbook.util.KwHelper;
import cn.ch4u.workbook.vo.HttpRes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
    @Resource
    private IMemberService memberService;

    @RequestMapping("add")
    public HttpRes addMember(@RequestBody Member member){
        memberService.save(member);
        //返回构建好的bean
        return new HttpRes(member);
    }

    @RequestMapping("update")
    public HttpRes updateMember(@RequestBody Member member){
        return new HttpRes(memberService.updateById(member));
    }

    @RequestMapping("del")
    public HttpRes delMember(int id){
        return new HttpRes(memberService.removeById(id));
    }

    @RequestMapping("all")
    public HttpRes findAll(){
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.orderByAsc("status","sort");
        return new HttpRes(memberService.list(wrapper));
    }

    @RequestMapping("allMap")
    public HttpRes findAllMap(){
        Map<Integer,Member> map=new LinkedHashMap<>();
        List<Member> list=(List<Member>) findAll().getData();
        if (!KwHelper.isNullOrEmpty(list)){
            Member member =null;
            for (int i = 0; i < list.size(); i++) {
                member =  list.get(i);
                if (member==null)continue;
                map.put(member.getId(),member);
            }
        }
        return new HttpRes(map);
    }

    @RequestMapping("get")
    public HttpRes find(int id){
        return new HttpRes(memberService.getById(id));
    }
}
