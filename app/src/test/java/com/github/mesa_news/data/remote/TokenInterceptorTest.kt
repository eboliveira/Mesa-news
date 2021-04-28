package com.github.mesa_news.data.remote

import com.github.mesa_news.data.local.SharedPreferences
import io.mockk.*
import okhttp3.Interceptor
import okhttp3.Request
import org.junit.After
import org.junit.Before
import org.junit.Test


class TokenInterceptorTest {
    private val subject = TokenInterceptor
    private val chainMock = mockk<Interceptor.Chain>(relaxed = true)
    private val builderMock = mockk<Request.Builder>(relaxed = true)
    private var token: String? = null

    @Before
    fun setupMocks() {
        val requestMock = mockk<Request>(relaxed = true)
        every { chainMock.request() } returns requestMock
        every { requestMock.newBuilder() } returns builderMock
        mockkObject(SharedPreferences)
    }

    @After
    fun teardown(){
        clearAllMocks()
    }

    @Test
    fun `intercept add authorization header when there is token on SharedPreferences`() {
        token = "a_token"
        every { SharedPreferences.token } returns token
        subject.intercept(chainMock)

        verify(exactly = 1) { builderMock.addHeader("Authorization", "Bearer $token") }
    }

    @Test
    fun `intercept don't add authorization header when there is no token on SharedPreferences`() {
        token = null
        every { SharedPreferences.token } returns token
        subject.intercept(chainMock)

        verify(exactly = 0) { builderMock.addHeader(any(), any()) }
    }
}
