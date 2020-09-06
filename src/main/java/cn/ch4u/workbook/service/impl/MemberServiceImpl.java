package cn.ch4u.workbook.service.impl;

import cn.ch4u.workbook.entity.Member;
import cn.ch4u.workbook.mapper.MemberMapper;
import cn.ch4u.workbook.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
