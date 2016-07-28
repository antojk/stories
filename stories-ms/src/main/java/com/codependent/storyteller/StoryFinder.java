package com.codependent.storyteller;

public interface StoryFinder {

	int count();

	Story[] findAll();

	Story findBy(int id);

}