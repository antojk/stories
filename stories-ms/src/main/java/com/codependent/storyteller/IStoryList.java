package com.codependent.storyteller;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IStoryList extends List<Story> {

	Stream<Story> filterBy(Predicate<Story> p);

}