package com.yl.api.member.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MemberUserEntity {

  private Long id;
  private String username;
  private String password;
  private String phone;
  private Integer sex;
  private String email;
  private Long created_at;
  private Long updated_at;

}
