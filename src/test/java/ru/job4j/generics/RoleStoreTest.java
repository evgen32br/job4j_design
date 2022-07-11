package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddThenRoleOne() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role one"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Role one"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role one"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role one"));
        store.add(new Role("1", "Role two"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Role one"));
    }

    @Test
    public void whenReplaceThenRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Role one"));
        store.replace("1", new Role("1", "Role two"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Role two"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "first"));
        store.replace("3", new Role("3", "third"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("first"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "first"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameFirst() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "first"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("first"));
    }
}