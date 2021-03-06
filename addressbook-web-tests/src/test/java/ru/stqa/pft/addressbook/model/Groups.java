package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import com.sun.prism.impl.FactoryResetException;

import java.util.Collection;
import java.util.HashSet;

import java.util.Set;

/**
 * Created by Даниил on 07.06.2017.
 */
public class Groups extends ForwardingSet<GroupData>{

  private Set<GroupData> delegate;

  public Groups(Groups groups){
    this.delegate = new HashSet<GroupData>(groups.delegate);

  }

  public Groups() {
    this.delegate = new HashSet<>();
  }

  public Groups(Collection<GroupData> groups) {
    this.delegate = new HashSet<GroupData>(groups);
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group ){
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group)
  {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
  public Groups withModified(GroupData modifiedGroup, GroupData group){
    Groups groups = new Groups(this);
    groups.remove(modifiedGroup);
    groups.add(group);
    return groups;
  }
}
