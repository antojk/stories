package com.codependent.storyteller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class StoryList extends ArrayList<Story> implements IStoryList {

	public StoryList(Story[] stories) {
		super(Arrays.asList(stories));
	}

	public StoryList() {
		super(10);
	}

	/* (non-Javadoc)
	 * @see com.codependent.storyteller.IStoryList#filterBy(java.util.function.Predicate)
	 */
	@Override
	public Stream<Story> filterBy(Predicate<Story> p) {
		return this.parallelStream().filter(p);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5475864054953757373L;

}
