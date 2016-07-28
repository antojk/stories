package com.codependent.storyteller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoryFinderImpl implements StoryFinder {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoryFinderImpl.class);

	private final IStoryList storyList;

	public StoryFinderImpl(IStoryList stories) {
		this.storyList = stories;
	}

	/* (non-Javadoc)
	 * @see com.codependent.storyteller.IStoryFinder#count()
	 */
	@Override
	public int count() {
		return storyList.size();
	}

	/* (non-Javadoc)
	 * @see com.codependent.storyteller.IStoryFinder#findAll()
	 */
	@Override
	public Story[] findAll() {
		return storyList.toArray(new Story[storyList.size()]);
	}

	/* (non-Javadoc)
	 * @see com.codependent.storyteller.IStoryFinder#findBy(int)
	 */
	@Override
	public Story findBy(int id) {
		Story missing = new Story();
		missing.setId(-1);
		missing.setName("Not Found!");
		return storyList.filterBy(story -> story.getId() == id).findAny().orElse(missing);
	}
}
