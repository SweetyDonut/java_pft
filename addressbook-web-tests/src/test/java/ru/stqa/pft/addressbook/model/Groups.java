package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

/**
 * Created by Даниил on 07.06.2017.
 */
public class Groups extends ForwardingSet<GroupData>{

  @Override
  protected Set<GroupData> delegate() {
    return null;
  }
}
