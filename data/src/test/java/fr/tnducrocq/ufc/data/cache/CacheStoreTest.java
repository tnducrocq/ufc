package fr.tnducrocq.ufc.data.cache;


import com.google.common.cache.Cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.tnducrocq.ufc.data.FakeDataProvider;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CacheStoreTest {

    @Mock
    private Cache<String, Fighter> mockCache;

    @Mock
    private Fighter mockCover;

    @InjectMocks
    private CacheStore<String, Fighter> cacheStore;

    @Before
    public void setUp() {
        given(mockCache.getIfPresent(FakeDataProvider.FAKE_FIGHTER_ID)).willReturn(mockCover);
        given(mockCache.size()).willReturn(Long.valueOf(1));
        cacheStore.put(FakeDataProvider.FAKE_FIGHTER_ID, mockCover);
    }

    @Test
    public void testGetStream() {
        cacheStore.getStream(FakeDataProvider.FAKE_FIGHTER_ID).subscribe(movie -> assertThat(movie, is(mockCover)));

        verify(mockCache).getIfPresent(FakeDataProvider.FAKE_FIGHTER_ID);
    }

    @Test
    public void testIsInCache() {
        assertThat(cacheStore.isInCache(FakeDataProvider.FAKE_FIGHTER_ID), is(true));

        verify(mockCache).getIfPresent(FakeDataProvider.FAKE_FIGHTER_ID);
    }

    @Test
    public void testInvalidate() {
        cacheStore.invalidate(FakeDataProvider.FAKE_FIGHTER_ID);
        verify(mockCache).invalidate(FakeDataProvider.FAKE_FIGHTER_ID);
    }

    @Test
    public void testSize() {
        assertThat(cacheStore.size(), is(1L));
        verify(mockCache).size();
    }
}
